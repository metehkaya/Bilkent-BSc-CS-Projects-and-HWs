% class names for each category
classNames =['n01615121';'n02099601';'n02123159';'n02129604';'n02317335';...
    'n02391049';'n02410509';'n02422699';'n02481823';'n02504458'];

mkdir('boundedImages2');

% get own results
fid = fopen('labels.txt');
tline = fgetl(fid);

% get real results
fid2 = fopen('test/bounding_box.txt');
tline2 = fgetl(fid2);

% total number of images
total = 0;

% classification accuracy
true = 0;

% wrong localization accuracy
wrongs=0;

% test image id
f=0;

% wrong localization counter for each category
wrongCnt = zeros(1,10);

% sum of ratios for each category
ratioSum = zeros(1,10);

% counter for each category
categoryCnt = zeros(1,10);

while ischar(tline)

    % split to coordinates
    C1 = strsplit(tline,'-');
    C2 = strsplit(tline2,',');
    index = str2num(C1{1});
    firstCo = [str2num(C1{3}),str2num(C1{4}),str2num(C1{5})+str2num(C1{3}),str2num(C1{6})+str2num(C1{4})];
    secondCo = [str2num(C2{2}),str2num(C2{3}),str2num(C2{4}),str2num(C2{5})];
    
    % if it belongs to the right category, increase classification accuracy
    if strcmp(C2{1},classNames(index,:)) == 1
        true=true+1;
    end
    
    % get the test image and draw correct rectangle
    nameStr = strcat('boundedImages/',string(f),'.jpeg');
    I = imread(convertStringsToChars( convertStringsToChars(nameStr) ));
    RGB = insertShape(I,'Rectangle',[str2num(C2{2}),str2num(C2{3}),(str2num(C2{4})-str2num(C2{2})),(str2num(C2{5})-str2num(C2{3}))],'LineWidth',3,'Color','red');
    imwrite(RGB ,convertStringsToChars(strcat('boundedImages2/',string(f),'.jpeg')));
    
    % calc overlap ratio
    overlapRatio = bboxOverlapRatio([str2num(C1{3}),str2num(C1{4}),...
        str2num(C1{5}),str2num(C1{6})],[str2num(C2{2}),str2num(C2{3}),...
        (str2num(C2{4})-str2num(C2{2})),(str2num(C2{5})-str2num(C2{3}))]);
    
    % increase counter for corresponding test image
    categoryCnt(1,index) = categoryCnt(1,index) + 1;
    
    % update sum of ratios for corresponding test image
    ratioSum(1,index) = ratioSum(1,index) + overlapRatio;
    
    % if it does NOT belong to the right category
    % or overlap ratio is smaller than 1/2
    % increase localization WRONG accuracy
    if overlapRatio < 0.5 || strcmp(C2{1},classNames(index,:)) ~= 1
        wrongCnt(1,index) = wrongCnt(1,index) + 1;
        wrongs=wrongs+1;
    end
    
    % if it belongs to the right category, declare window with specify ratio
    if strcmp(C2{1},classNames(index,:)) == 1
        RGB = insertText(RGB,[str2num(C1{3})+1,str2num(C1{4})+1],{convertStringsToChars(strcat('overlap ratio:',{' '},string(bboxOverlapRatio([str2num(C1{3}),str2num(C1{4}),...
        str2num(C1{5}),str2num(C1{6})],[str2num(C2{2}),str2num(C2{3}),...
        (str2num(C2{4})-str2num(C2{2})),(str2num(C2{5})-str2num(C2{3}))]))))},'TextColor','black','BoxOpacity',0.4,'FontSize',11);
        RGB = insertText(RGB,[str2num(C1{3})+1,str2num(C1{4})+21],{convertStringsToChars(strcat('true class:',{' '},string(C2{1}),{' '}))},'TextColor','black','BoxOpacity',0.4,'FontSize',11);
        RGB = insertText(RGB,[str2num(C1{3})+1,str2num(C1{4})+41],{convertStringsToChars(strcat('predicted class:',{' '},string(classNames(index,:))))},'TextColor','black','BoxOpacity',0.4,'FontSize',11);
    % otherwise, declare only categories
    else
        RGB = insertText(RGB,[str2num(C1{3})+1,str2num(C1{4})+1],{convertStringsToChars(strcat('true class:',{' '},string(C2{1}),{' '}))},'TextColor','black','BoxOpacity',0.4,'FontSize',11);
        RGB = insertText(RGB,[str2num(C1{3})+1,str2num(C1{4})+21],{convertStringsToChars(strcat('predicted class:',{' '},string(classNames(index,:))))},'TextColor','black','BoxOpacity',0.4,'FontSize',11);
    end
    
    % save the image
    imwrite(RGB ,convertStringsToChars(strcat('boundedImages2/',string(f),'.jpeg')));
    total = total+1;
    tline = fgetl(fid);
    tline2 = fgetl(fid2);
    
    f=f+1;
    
end

% calc ratio average for each category
ratioAvg = zeros(1,10);
for i = 1 : 10
    ratioAvg(1,i) = ratioSum(1,i) / categoryCnt(1,i);
end

% display ratios, etc.
disp("Total # of image: " + total);
disp("Classification accuracy: " + true);
disp("Classification wrong accuracy: " + (total - true));
disp("Localization accuracy: " + (total - wrongs));
disp("Localization wrong accuracy: " + wrongs);
disp("Total # of Localization wrong images: ")
disp(wrongCnt);
disp("Average of ratio for each category: ")
disp(ratioAvg);