% Metehan Kaya - CS IV - 21401258

source_image = [ 0,0,0,0,0,0,0 ; 0,0,0,0,0,0,0 ; 0,0,1,1,1,0,0 ; 0,0,1,1,1,0,0 ; 0,0,1,1,1,0,0 ; 0,0,0,0,0,0,0 ; 0,0,0,0,0,0,0 ];
struct_el = [ 1,1,1 ; 1,1,1 ; 1,1,1 ];

binary_image_1 = dilation( source_image , struct_el );
figure; imshow( binary_image_1 );

binary_image_2 = erosion( source_image , struct_el );
figure; imshow( binary_image_2 );