% Metehan Kaya - CS IV - 21401258

mystel_1 = [ 0,1,0 ; 1,1,1 ; 0,1,0 ];
mystel_2 = [ 0,0,1,0,0 ; 0,1,1,1,0 ; 1,1,1,1,1 ; 0,1,1,1,0 ; 0,0,1,0,0 ];
mystel_3 = [ 0,0,0,1,0,0,0 ; 0,0,1,1,1,0,0 ; 0,1,1,1,1,1,0 ; 1,1,1,1,1,1,1 ; 0,1,1,1,1,1,0 ; 0,0,1,1,1,0,0 ; 0,0,0,1,0,0,0 ];
mystel_4 = ones(3,3);
mystel_5 = ones(5,5);

[ n, m, numBands ] = size( imread( 'data\\copyMachine\\0.png' ) );
grey = zeros( n , m , 4 );
tval = [ 0 ; 48 ; 42 ; 50 ];

for i = 1 : 4
    inputName = [ 'data\\copyMachine\\' , int2str(i-1) , '.png' ];
    input_image = imread( inputName );
    red = input_image(:,:,1);
    green = input_image(:,:,2);
    blue = input_image(:,:,3);
    grey(:,:,i) = ( red / 3.0 + green / 3.0 + blue / 3.0 );
    if i > 1
        diff = grey(:,:,1) - grey(:,:,i);
        binary_image = ( diff > tval(i,1) );
        if i == 2
            binary_image = erosion( binary_image , mystel_3 );
            binary_image = dilation( binary_image , mystel_3 );
            binary_image = dilation( binary_image , mystel_2 );
            binary_image = dilation( binary_image , mystel_2 );
        elseif i == 3
            binary_image = erosion( binary_image , mystel_2 );
            binary_image = dilation( binary_image , mystel_2 );
            binary_image = dilation( binary_image , mystel_2 );
            binary_image = erosion( binary_image , mystel_2 );
        elseif i == 4
            binary_image = erosion( binary_image , mystel_3 );
            binary_image = dilation( binary_image , mystel_3 );
            binary_image = dilation( binary_image , mystel_1 );
        end
        c = bwlabel( binary_image , 4 );
        col = max( c(:) );
        c2 = label2rgb( c , jet( col ) , [ 0 0 0 ] );
        figure; imshow( c2 );
        outputName = [ 'Q4_output_copyMachine_' , int2str(i-1) , '.png' ];
        imwrite( c2 , outputName );
    end
end

[ n, m, numBands ] = size( imread( 'data\\station\\0.png' ) );
grey = zeros( n , m , 4 );
tval = [ 0 ; 53 ; 45 ; 35 ];

for i = 1 : 4
    inputName = [ 'data\\station\\' , int2str(i-1) , '.png' ];
    input_image = imread( inputName );
    red = input_image(:,:,1);
    green = input_image(:,:,2);
    blue = input_image(:,:,3);
    grey(:,:,i) = ( red / 3.0 + green / 3.0 + blue / 3.0 );
    if i > 1
        diff = grey(:,:,1) - grey(:,:,i);
        binary_image = ( diff > tval(i,1) );
        if i == 2
            binary_image = erosion( binary_image , mystel_3 );
            binary_image = dilation( binary_image , mystel_3 );
            binary_image = dilation( binary_image , mystel_1 );
            binary_image = erosion( binary_image , mystel_1 );
        elseif i == 3
            binary_image = erosion( binary_image , mystel_1 );
            binary_image = dilation( binary_image , mystel_1 );
            binary_image = dilation( binary_image , mystel_2 );
        elseif i == 4
            binary_image = erosion( binary_image , mystel_1 );
            binary_image = dilation( binary_image , mystel_1 );
            binary_image = dilation( binary_image , mystel_2 );
        end
        c = bwlabel( binary_image , 4 );
        col = max( c(:) );
        c2 = label2rgb( c , jet( col ) , [ 0 0 0 ] );
        figure; imshow( c2 );
        outputName = [ 'Q4_output_station_' , int2str(i-1) , '.png' ];
        imwrite( c2 , outputName );
    end
end