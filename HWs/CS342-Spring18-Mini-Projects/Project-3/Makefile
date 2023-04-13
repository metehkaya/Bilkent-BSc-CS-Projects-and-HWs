obj-m = mymodule.o

all:	p1 p2

p1:	mymodule.c
	make -C /lib/modules/$(shell uname -r)/build/ M=$(PWD) modules
	
p2:	app.c
	gcc	-Wall	-g	-o	app	app.c

clear:
	make -C /lib/modules/$(shell uname -r)/build/ M=$(PWD) clean
