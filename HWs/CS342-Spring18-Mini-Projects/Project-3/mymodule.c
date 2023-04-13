#include <linux/init.h>
#include <linux/module.h>
#include <linux/moduleparam.h>
#include <linux/kernel.h>
#include <linux/stat.h>
#include <linux/sched/signal.h>
#include <linux/mm.h>
#include <linux/mm_types.h>
#include <linux/proc_fs.h>
#include <asm/pgtable.h>

MODULE_LICENSE("GPL");

static int processid;
module_param(processid, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
MODULE_PARM_DESC(processid, "An integer");

static void print_mem( struct task_struct *task_list ) {
	
	struct mm_struct *mm;
	mm = task_list->mm;
	struct vm_area_struct *vma;
	
	// argument segment
	unsigned long arg_start = mm->arg_start;
	unsigned long arg_end = mm->arg_end;
	unsigned long arg_size_hex = arg_end - arg_start;
	
	// environment var. segment
	unsigned long env_start = mm->env_start;
	unsigned long env_end = mm->env_end;
	unsigned long env_size_hex = env_end - env_start;
	
	// virtual mem segment
	unsigned long vm_count = mm->total_vm;
	unsigned long vm_size = (vm_count << 2);
	
	// number of frames
	unsigned long noOfFrames = get_mm_rss(mm);
	unsigned long frameSize = (noOfFrames << 2);
	
	// code segment
	unsigned long code_start = mm->start_code;
	unsigned long code_end =  mm->end_code;
	unsigned long code_size_hex = code_end - code_start;
	
	// data segment
	unsigned long data_start = mm->start_data;
	unsigned long data_end = mm->end_data;
	unsigned long data_size_hex = data_end - data_start; 
	
	// heap segment
	unsigned long heap_start = mm->start_brk;
	unsigned long heap_end = mm->brk;
	unsigned long heap_size_hex = heap_end - heap_start;
	
	// stack segment
	vma = mm->mmap;
	while(vma != NULL) {
		if(vma->vm_flags & VM_STACK)
			break;
		vma = vma->vm_next;
	}
	
	unsigned long stack_start = vma->vm_start;
	unsigned long stack_end = vma->vm_end;
	unsigned long stack_size_hex = stack_end - stack_start;	
	
	printk( "Size of virtual memory  = %d KB\n"
		"Number of frames = %d\n"
		"Size of used frames = %d\n"
		"Code  Segment start address= 0x%lx, end address= 0x%lx, size = %d B\n"
		"Data  Segment start address= 0x%lx, end address= 0x%lx, size = %d B\n"
		"Heap  Segment start address= 0x%lx, end address= 0x%lx, size = %d B\n"
		"Stack  Segment start address= 0x%lx, end address= 0x%lx, size = %d B\n"
		"Main Arguments start address= 0x%lx, end address= 0x%lx, size = %d B\n"
		"Environment variables start address= 0x%lx, end address= 0x%lx, size = %d B\n" ,
		vm_size, noOfFrames, frameSize,
		code_start, code_end, code_size_hex,
		data_start, data_end, data_size_hex,
        heap_start, heap_end, heap_size_hex,
        stack_start, stack_end, stack_size_hex,
        arg_start, arg_end, arg_size_hex,
        env_start, env_end, env_size_hex
	);
	
    // page table
    pgd_t *pgd;
    pgd = mm->pgd;
	
	int i;
    for( i = 0 ; i < 512 ; i++ ) {
		if( !pgd_none(pgd[i]) && !pgd_bad(pgd[i]) && 
		( (pgd[i].pgd & 32) == 32 ) && ( (pgd[i].pgd & 1) == 1 ) &&
		( (pgd[i].pgd & 2) == 2 ) && ( (pgd[i].pgd & 4) == 4 ) &&
		( (pgd[i].pgd & 8) == 0 ) && ( (pgd[i].pgd & 16) == 0 ) &&
		( (pgd[i].pgd & 128) == 0 )) {					
			printk("%dth entry = 0x%lx\n", i, pgd[i].pgd);
		}
	}	
	
}

static int hello_init(void) {
	
	struct task_struct *task;

	for_each_process(task) {
		if ( task->pid == processid ) {
			printk( "PID IS %s[%d]\n" , task->comm , task->pid );
            print_mem(task);
		}
	}
	
	return 0;
	
}

static void hello_exit(void) {
    printk(KERN_ALERT "Goodbye, cruel world\n");
}

module_init(hello_init);
module_exit(hello_exit);
