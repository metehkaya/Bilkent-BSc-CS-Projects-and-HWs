% Metehan Kaya - 21401258

close all;

% Get input
promptDataFileName = 'Text file listing a set of file names (files.txt): ';
dataFileName = input( promptDataFileName , 's' );
promptDataClassId = 'Text file listing a set of category ids (classes.txt): ';
dataClassId = input( promptDataClassId , 's' );
promptDescriptorType = 'Choose descriptor type (Enter 1 for gradient, 2 for color, 3 for both): ';
userDescriptorType = input( promptDescriptorType );
promptCodebookSize = 'Choose codebook size (Enter 100 or 500): ';
userCodebookSize = input( promptCodebookSize );

imageAnalysis( dataFileName , dataClassId , userDescriptorType , userCodebookSize );

% Main function
function imageAnalysis( dataFileName , dataClassId , userDescriptorType , userCodebookSize )

    dataFilePath = strcat( 'data/' , dataFileName );
    dataClassPath = strcat( 'data/' , dataClassId );
    
    % Get Class ids from the given text file
    classIds = [];
    fid = fopen( dataClassPath , 'rt' );
    while true
        imageClassId = fgetl( fid );
        if ~ischar(imageClassId); break;
        else
            classId = str2num( imageClassId );
            classIds = [classIds classId];
        end
    end
    fclose(fid);

    imageId = 0;
    gradientMatrix = [];
    colorMatrix = [];
    mixedMatrix = [];
    noInterest = [];

    markClass = zeros(1,10);
    classNames = [ "beach" , "field" , "lake" , "mountain" , "office" , "park" , "restaurant" , "street" , "supermarket" , "village" ];

    % Get descriptors of each image and merge them into matrices
    fid = fopen( dataFilePath , 'rt' );
    while true
        imageName = fgetl( fid );
        if ~ischar(imageName); break;
        else
            imageId = imageId + 1;
            classId = classIds( 1 , imageId );
            fprintf( "Image %d , Class %d\n" , imageId , classId );
            [ gradientDesc , colorDesc , mixedDesc ] = calcAllDesc( imageName , markClass(1,classId) , classNames(1,classId) );
            markClass(1,classId) = 1;
            noInterestImage = size( gradientDesc , 2 );
            gradientMatrix = [ gradientMatrix gradientDesc ];
            colorMatrix = [ colorMatrix colorDesc ];
            mixedMatrix = [ mixedMatrix mixedDesc ];
            noInterest = [ noInterest noInterestImage ];
        end
    end
    fclose(fid);

    % Get histograms that are resulted from kmeans
    fprintf( "Calc K-Mean\n" );
    K1 = 100;
    K2 = 500;
    noImage = imageId;
    allHists = getKMean( gradientMatrix , colorMatrix , mixedMatrix , noInterest , noImage , K1 , K2 , classIds , classNames , userDescriptorType , userCodebookSize );

    % Plot them
    fprintf( "TSNE\n" );
    for type = 1 : 6
        plotTSNE( allHists , type , classIds , classNames , K1 , K2 )
    end
    
end

% t-SNE plotting function
function plotTSNE( allHists , type , classIds , classNames , K1 , K2 )
    noImage = size( classIds , 2 );
    labels = [];
    hist = [];
    useClass = [ 1 , 0 , 1 , 0 , 0 , 0 , 1 , 0 , 0 , 0 ];
    for imageId = 1 : noImage
        classId = classIds( 1 , imageId );
        if useClass( 1 , classId ) >= 0
            labels = [ labels ; classNames( 1 , classId ) ];
            hist = [ hist ; allHists{ imageId , type } ];
        end
    end
    mappedX = tsne(hist);
%     no_dims = 2;
%     initial_dims = 50;
%     perplexity = 30;
%     mappedX = tsne( hist , [] , no_dims , initial_dims , perplexity );
    figure; gscatter(mappedX(:,1), mappedX(:,2),labels);
    titleName = [];
    if type == 1
        titleName = [ 'Gradient-Based t-SNE Visualization ' , int2str(K1) ];
    elseif type == 2
        titleName = [ 'Color-Based t-SNE Visualization ' , int2str(K1) ];
    elseif type == 3
        titleName = [ 'Mixed t-SNE Visualization ' , int2str(K1) ];
    elseif type == 4
        titleName = [ 'Gradient-Based t-SNE Visualization ' , int2str(K2) ];
    elseif type == 5
        titleName = [ 'Color-Based t-SNE Visualization ' , int2str(K2) ];
    elseif type == 6
        titleName = [ 'Mixed t-SNE Visualization ' , int2str(K2) ];
    end
    title( titleName );
end

% k-mean function
function allHists = getKMean( gradientMatrix , colorMatrix , mixedMatrix , noInterest , noImage , K1 , K2 , classIds , classNames , userDescriptorType , userCodebookSize )
    
    noAllInterests = size( gradientMatrix , 2 );
    allHists = {};
    idInterest = 0;
    markClass = zeros(1,10);
    
    % Get centers of clusters and assignments of each interest point
    [centerG1 , assignmentG1 ] = vl_kmeans( gradientMatrix , K1 );
    [centerC1 , assignmentC1 ] = vl_kmeans( colorMatrix , K1 );
    [centerM1 , assignmentM1 ] = vl_kmeans( mixedMatrix , K1 );
    [centerG2 , assignmentG2 ] = vl_kmeans( gradientMatrix , K2 );
    [centerC2 , assignmentC2 ] = vl_kmeans( colorMatrix , K2 );
    [centerM2 , assignmentM2 ] = vl_kmeans( mixedMatrix , K2 );
    
    for imageId = 1 : noImage
        
        cntImageInterest = noInterest( 1 , imageId );
        histG1 = zeros( 1 , K1 );
        histC1 = zeros( 1 , K1 );
        histM1 = zeros( 1 , K1 );
        histG2 = zeros( 1 , K2 );
        histC2 = zeros( 1 , K2 );
        histM2 = zeros( 1 , K2 );
        
        % calc hists
        for idImageInterest = 1 : cntImageInterest
            idInterest = idInterest + 1;
            histG1( 1 , assignmentG1(1,idInterest) ) = histG1( 1 , assignmentG1(1,idInterest) ) + 1;
            histC1( 1 , assignmentC1(1,idInterest) ) = histC1( 1 , assignmentC1(1,idInterest) ) + 1;
            histM1( 1 , assignmentM1(1,idInterest) ) = histM1( 1 , assignmentM1(1,idInterest) ) + 1;
            histG2( 1 , assignmentG2(1,idInterest) ) = histG2( 1 , assignmentG2(1,idInterest) ) + 1;
            histC2( 1 , assignmentC2(1,idInterest) ) = histC2( 1 , assignmentC2(1,idInterest) ) + 1;
            histM2( 1 , assignmentM2(1,idInterest) ) = histM2( 1 , assignmentM2(1,idInterest) ) + 1;
        end
        
        % normalization of the hists
        histG1 = histG1 / norm(histG1);
        histC1 = histC1 / norm(histC1);
        histM1 = histM1 / norm(histM1);
        histG2 = histG2 / norm(histG2);
        histC2 = histC2 / norm(histC2);
        histM2 = histM2 / norm(histM2);
        
        allHists{imageId,1} = histG1;
        allHists{imageId,2} = histC1;
        allHists{imageId,3} = histM1;
        allHists{imageId,4} = histG2;
        allHists{imageId,5} = histC2;
        allHists{imageId,6} = histM2;
        
        % bar plots of codebooks
        classId = classIds( 1 , imageId );
        className = classNames( 1 , classId );
        if markClass(1,classId) == 0
            if userCodebookSize == K1 && userDescriptorType == 1
                figure; bar( histG1 );
                titleName = [ 'Gradient-Based Codebook Bar Chart ' , className , int2str(K1) ];
                title( titleName );
            elseif userCodebookSize == K1 && userDescriptorType == 2
                figure; bar( histC1 );
                titleName = [ 'Color-Based Codebook Bar Chart ' , className , int2str(K1) ];
                title( titleName );
            elseif userCodebookSize == K1 && userDescriptorType == 3
                figure; bar( histM1 );
                titleName = [ 'Mixed Codebook Bar Chart ' , className , int2str(K1) ];
                title( titleName );
            elseif userCodebookSize == K2 && userDescriptorType == 1
                figure; bar( histG2 );
                titleName = [ 'Gradient-Based Codebook Bar Chart ' , className , int2str(K2) ];
                title( titleName );
            elseif userCodebookSize == K2 && userDescriptorType == 2
                figure; bar( histC2 );
                titleName = [ 'Color-Based Codebook Bar Chart ' , className , int2str(K2) ];
                title( titleName );
            elseif userCodebookSize == K2 && userDescriptorType == 3
                figure; bar( histM2 );
                titleName = [ 'Mixed Codebook Bar Chart ' , className , int2str(K2) ];
                title( titleName );
            end
        end
        markClass(1,classId) = 1;
        
    end
    
end

% function responsible for calc of descriptors
function [ gradientDesc , colorDesc , mixedDesc ] = calcAllDesc( imageName , markClass , className )
    
    imagePath = strcat( 'data/' , imageName );
    imageRGB = imread( imagePath );
    [ height , width , numBands ] = size( imageRGB );
    % figure; imshow( imageRGB );
    imageGRAY = single( rgb2gray(imageRGB) );
    [ tempFrames , tempGradientDesc ] = vl_sift( imageGRAY );
    
    noInterest = size( tempFrames , 2 );
    if noInterest < 50
        fprintf( "!!! ALERT !!!\n" );
    end
    
    % choose only 50 interest points randomly
    frames = [];
    gradientDesc = [];
    perm = randperm( noInterest );
    for i = 1 : 50
        frames = [ frames tempFrames(:,perm(i)) ];
        gradientDesc = [ gradientDesc tempGradientDesc(:,perm(i)) ];
    end
    
%     sel = noInterest;
%     h1 = vl_plotframe(frames(:,sel));
%     h2 = vl_plotframe(frames(:,sel));
%     set(h1,'color','k','linewidth',3) ;
%     set(h2,'color','y','linewidth',2) ;
%     h3 = vl_plotsiftdescriptor(gradientDesc(:,sel),frames(:,sel));
%     set(h3,'color','g');
    
    gradientDesc = double(gradientDesc);
    colorDesc = getColorDescription( imageRGB , frames );
    mixedDesc = [ gradientDesc ; colorDesc ];
    
    % plot descriptors
    if markClass == 0
        figure; bar( gradientDesc );
        titleName = [ 'Gradient-Based Descriptor Bar Chart of ' , className ];
        title( titleName );
        figure; bar( colorDesc );
        titleName = [ 'Color-Based Descriptor Bar Chart of ' , className ];
        title( titleName );
        figure; bar( mixedDesc );
        titleName = [ 'Mixed Descriptor Bar Chart of ' , className ];
        title( titleName );
    end
    
end

% function responsible for color-based descriptors
function colorDesc = getColorDescription( imageRGB , frames )
    noInterest = size( frames , 2 );
    [ height , width , numBands ] = size( imageRGB );
    colorDesc = zeros( 64 , noInterest );
    for idInterest = 1 : noInterest
        % calc of corner points & rotation
        centerX = frames(1,idInterest);
        centerY = frames(2,idInterest);
        scale = frames(3,idInterest);
        theta = frames(4,idInterest);
        dirs = zeros( 2 , 4 );
        dirs(1,1) = -6*scale; dirs(2,1) = +6*scale;
        dirs(1,2) = -6*scale; dirs(2,2) = -6*scale;
        dirs(1,3) = +6*scale; dirs(2,3) = -6*scale;
        dirs(1,4) = +6*scale; dirs(2,4) = +6*scale;
        R = [cos(theta) -sin(theta); sin(theta) cos(theta)];
        points = zeros( 2 , 4 );
        for dirType = 1 : 4
            points(:,dirType) = R*dirs(:,dirType) + [centerX ; centerY];
        end
        minX = min( points(1,:) ); maxX = max( points(1,:) );
        minY = min( points(2,:) ); maxY = max( points(2,:) );
        minX = max( minX , 1 ); maxX = min( maxX , width );
        minY = max( minY , 1 ); maxY = min( maxY , height );
        rowMin = int16(minY); rowMax = int16(maxY);
        colMin = int16(minX); colMax = int16(maxX);
        inSquarePoints = [];
        % look for potential pixels
        for col = colMin : colMax
            for row = rowMin : rowMax
                inSquare = checkInside( col , row , points );
                inPoint = [col;row];
                % check if the pixel is in rotated square
                if inSquare == 1
                    redPixel = imageRGB(row,col,1);
                    red = floor( calcFloor64( redPixel ) );
                    greenPixel = imageRGB(row,col,2);
                    green = floor( calcFloor64( greenPixel ) );
                    bluePixel = imageRGB(row,col,3);
                    blue = floor( calcFloor64( bluePixel ) );
                    ind = red*16 + green*4 + blue*1 + 1;
                    colorDesc(ind,idInterest) = colorDesc(ind,idInterest) + 1;
                    inSquarePoints = [ inSquarePoints , inPoint ];
                end
            end
        end
    end
end

% function responsible for floor(value/64)
function id = calcFloor64( value )
    id = -1;
    if value < 64
        id = 0;
    elseif value < 128
        id = 1;
    elseif value < 192
        id = 2;
    else
        id = 3;
    end
end

% CCW method that checks if the pixel is in the square
function inSquare = checkInside( x , y , points )
    inSquare = 0;
    counter1 = 0;
    counter2 = 0;
    p1x = points(1,1); p1y = points(2,1);
    p2x = points(1,2); p2y = points(2,2);
    p3x = points(1,3); p3y = points(2,3);
    p4x = points(1,4); p4y = points(2,4);
    if (p2x-p1x)*(y-p1y) - (p2y-p1y)*(x-p1x) <= 0
        counter1 = counter1 + 1;
    elseif (p2x-p1x)*(y-p1y) - (p2y-p1y)*(x-p1x) >= 0
        counter2 = counter2 + 1;
    end
    if (p3x-p2x)*(y-p2y) - (p3y-p2y)*(x-p2x) <= 0
        counter1 = counter1 + 1;
    elseif (p3x-p2x)*(y-p2y) - (p3y-p2y)*(x-p2x) >= 0
        counter2 = counter2 + 1;
    end
    if (p4x-p3x)*(y-p3y) - (p4y-p3y)*(x-p3x) <= 0
        counter1 = counter1 + 1;
    elseif (p4x-p3x)*(y-p3y) - (p4y-p3y)*(x-p3x) >= 0
        counter2 = counter2 + 1;
    end
    if (p1x-p4x)*(y-p4y) - (p1y-p4y)*(x-p4x) <= 0
        counter1 = counter1 + 1;
    elseif (p1x-p4x)*(y-p4y) - (p1y-p4y)*(x-p4x) >= 0
        counter2 = counter2 + 1;
    end
    if counter1 == 4
        inSquare = 1;
    end
    if counter2 == 4
        inSquare = 1;
    end
end