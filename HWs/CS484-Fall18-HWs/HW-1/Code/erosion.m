% Metehan Kaya - CS IV - 21401258

function binary_image = erosion( source_image , struct_el )
    [ n , m ] = size( source_image );
    [ h , w ] = size( struct_el );
    a = ( h - 1 ) / 2;
    b = ( w - 1 ) / 2;
    binary_image = ones( n , m );
    for i = 1 : n
        for j = 1 : m
            for r = -a : a
                if i + r >= 1 && i + r <= n
                    for c = -b : b
                        if j + c >= 1 && j + c <= m
                            if struct_el( r + a + 1 , c + b + 1 ) == 1 && source_image( i + r , j + c ) == 0
                                binary_image( i , j ) = 0;
                            end
                        end
                    end
                end
            end
        end
    end
end