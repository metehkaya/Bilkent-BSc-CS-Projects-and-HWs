% Metehan Kaya - CS IV - 21401258

input_image = imread( 'data\\airplane_graveyard.jpg' );
[ n, m, numBands ] = size( input_image );

red = input_image( :, :, 1 );
green = input_image( :, :, 2 ); 
blue = input_image( :, :, 3 );

binary_image = ( blue >= 170 & blue >= green * 0.85 & blue >= red * 0.95 );
figure; imshow( binary_image );

struct_el_3_3_1 = [ 1 0 0 ; 0 1 0 ; 0 0 1 ];
binary_image_1 = binary_image;
binary_image_1 = erosion( binary_image_1 , struct_el_3_3_1 );
binary_image_1 = dilation( binary_image_1 , struct_el_3_3_1 );

struct_el_3_3_2 = [ 0 0 1 ; 0 1 0 ; 1 0 0 ];
binary_image_2 = binary_image;
binary_image_2 = erosion( binary_image_2 , struct_el_3_3_2 );
binary_image_2 = dilation( binary_image_2 , struct_el_3_3_2 );

struct_el_1_3_1 = ones(1,3);
binary_image_3 = binary_image;
binary_image_3 = erosion( binary_image_3 , struct_el_3_3_2 );
binary_image_3 = dilation( binary_image_3 , struct_el_3_3_2 );

binary_image = (binary_image_1 | binary_image_2 | binary_image_3);
figure; imshow( binary_image );

mark = zeros( n , m );
ncc = 0;
link_cc = [];

for r = 1 : n
    for c = 1 : m
        if binary_image(r,c) == 1
            valueTop = -1 ;
            valueLeft = -1;
            if( r > 1 && binary_image(r-1,c) == 1 )
                valueTop = mark(r-1,c);
            end
            if( c > 1 && binary_image(r,c-1) == 1 )
                valueLeft = mark(r,c-1);
            end
            if valueTop == -1 && valueLeft == -1
                ncc = ncc + 1;
                mark(r,c) = ncc;
            end
            if valueTop ~= -1 && valueLeft == -1
                mark(r,c) = valueTop;
            end
            if valueTop == -1 && valueLeft ~= -1
                mark(r,c) = valueLeft;
            end
            if valueTop ~= -1 && valueLeft ~= -1
                mark(r,c) = valueTop;
                if valueTop ~= valueLeft
                    ccs = [valueTop , valueLeft];
                    link_cc = [link_cc ; ccs];
                end
            end
        end
    end
end

par = dsu( link_cc , ncc );

colorsRGB = [ 153 0 0 ; 255 0 0 ; 255 102 102 ; 102 51 0 ; 255 128 0 ; 102 102 0 ; 255 255 0 ; 102 204 0 ; 204 255 153 ; 102 255 178 ; 0 255 255 ; 0 51 102 ; 0 128 255 ; 102 178 255 ; 76 0 153 ; 178 102 255 ; 255 0 255 ; 64 64 64 ; 192 192 192 ; 0 0 0 ];
[ colors_no, colors_bands ] = size( colorsRGB );

tree_size = zeros( ncc , 1 );
group_id = zeros( n , m );
root_id = zeros( n , m );

for i = 1 : n
    for j = 1 : m
        if binary_image(i,j) == 1
            group_id(i,j) = mark(i,j);
            root_id(i,j) = par( group_id(i,j) , 1 );
            tree_size( root_id(i,j) , 1 ) = tree_size( root_id(i,j) , 1 ) + 1;
        end
    end
end

imageRGB_red = zeros( n , m );
imageRGB_green = zeros( n , m );
imageRGB_blue = zeros( n , m );

for i = 1 : n
    for j = 1 : m
        imageRGB_red(i,j) = 255;
        imageRGB_green(i,j) = 255;
        imageRGB_blue(i,j) = 255;
        if binary_image(i,j) == 1 && tree_size( root_id(i,j) , 1 ) <= 2000
            color_id = mod( root_id(i,j) - 1 , colors_no ) + 1;
            imageRGB_red(i,j) = colorsRGB( color_id , 1 );
            imageRGB_green(i,j) = colorsRGB( color_id , 2 );
            imageRGB_blue(i,j) = colorsRGB( color_id , 3 );
        end
    end
end

imageRGB = zeros( n , m , 3 );
imageRGB( :, :, 1 ) = imageRGB_red;
imageRGB( :, :, 2 ) = imageRGB_green;
imageRGB( :, :, 3 ) = imageRGB_blue;
figure; imshow( uint8( imageRGB ) );
imwrite( uint8( imageRGB ), 'Q3_output.png' );

function par = dsu( link_cc , ncc )

    par = [];
    for i = 1 : ncc
        par = [par ; i];
    end
    depth = zeros( ncc , 1 );
    [ link_sz , link_width ] = size( link_cc );
    
    for i = 1 : link_sz
        a = link_cc( i , 1 );
        b = link_cc( i , 2 );
        [a , par] = findPar( a , par );
        [b , par] = findPar( b , par );
        if a == b
            continue
        end
        if depth( a , 1 ) == depth( b , 1 )
            par( a , 1 ) = b;
            depth( b , 1 ) = depth( a , 1 ) + 1;
        elseif depth( a , 1 ) < depth( b , 1 )
            par( a , 1 ) = b;
        else
            par( b , 1 ) = a;
        end
    end
    
    for i = 1 : ncc
        a = i;
        [a , par] = findPar( a , par );
    end
    
end

function [par_id , par] = findPar( node , par )
    par_id = 0;
    if par(node,1) == node
        par_id = node;
    else
        [par_id , par] = findPar( par(node,1) , par );
        par(node,1) = par_id;
    end
end