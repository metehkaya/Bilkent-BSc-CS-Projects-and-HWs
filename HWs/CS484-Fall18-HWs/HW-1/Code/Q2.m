% Metehan Kaya - CS IV - 21401258

input_image = imread( 'data\\sonnet.png' );
[ n, m, numBands ] = size( input_image );

sum = zeros( n , m );
cnt = zeros( n , m );

h = ones(53,41);
h = h./2173;
avg = conv2( input_image , h , 'same' );

temp_image = ( input_image > avg - 7.5 );
figure; imshow( temp_image );
temp_image = ~temp_image;

struct_el_1_3_1 = ones(1,3);
struct_el_3_1_1 = ones(3,1);

output_image_1 = temp_image;
output_image_1 = erosion( output_image_1 , struct_el_1_3_1 );
output_image_1 = dilation( output_image_1 , struct_el_1_3_1 );

output_image_2 = temp_image;
output_image_2 = erosion( output_image_2 , struct_el_3_1_1 );
output_image_2 = dilation( output_image_2 , struct_el_3_1_1 );

output_image = ( output_image_1 | output_image_2 );

output_image = ~output_image;
figure; imshow( output_image );
imwrite( output_image , 'Q2_output.png' );