clear classes
models = proje();
model=load('modelBsds'); model=model.model;
model.opts.multiscale=0; model.opts.sharpen=2; model.opts.nThreads=4;
opts = edgeBoxes;
opts.alpha = .65;     % step size of sliding window search
opts.beta  = .75;     % nms threshold for object proposals
opts.minScore = 0;  % min score of boxes to detect
opts.maxBoxes = 50;  % max number of boxes to detect
tic,
classNames =['n01615121';'n02099601';'n02123159';'n02129604';'n02317335';...
    'n02391049';'n02410509';'n02422699';'n02481823';'n02504458'];
s = ' ';

mkdir('boundedImages');

% n01615121 = 1 % n02099601 = 2 % n02123159 = 3 % n02129604 = 4 % n02317335 = 5
% n02391049 = 6 % n02410509 = 7 % n02422699 = 8 % n02481823 = 9 % n02504458 = 10

% results will be written to labels text file
labelsAndScores = [];
try
    fileIDS = fopen('labels.txt','w');
catch
    disp("Exception catched");
end

i = 0;
maxClassScore=[-100,-100];

while i<=99
    
    % read test image
    tic,
    disp(i);
    maxBoundingBoxScore=-1;
    nameStr = strcat('test/images/',string(i),'.JPEG');
    nameStr = convertStringsToChars(nameStr);
    I = imread(nameStr);
    
    bbs=edgeBoxes(I,model,opts);
    j = 1;
    
    % predicted category
    predictedLabel=-1;
    
    % max class score and corresponding window id
    maxClassScore = [-100,-100];
    
    % check the score on each category
    while j<=50
        tic,
        disp(strcat('for i''', string(i),' and  j''',string(j)));
        im = imcrop(I,bbs(j,1:4));
        nameStr2 = strcat(string(i),'f.jpg');
        nameStr2 = convertStringsToChars(nameStr2);
        imwrite(im,nameStr2);
        try
            fileID = fopen('memory.txt','w');
            fprintf(fileID,nameStr2);
            fclose(fileID);
        catch
            disp("Exception catched");
        end
        mod = py.importlib.import_module('forOne');
        py.importlib.reload(mod);
        a = py.forOne.featurization;
        k = 1;
        nameStr3 = strcat(string(i),'f.mat');
        % check the score on each category
        while k<=10
             % get the score, and update category
             [label,score] = predictImage(models{k},nameStr3);
             if score(1,2) > maxClassScore(1,1)
                maxClassScore = [score(1,2),j];
                predictedLabel = k;
            end
            k=k+1;
        end
        j=j+1;
        toc;
    end
    
    % if there is a window with a valid score
    if maxClassScore(1,1) ~= -100
        fprintf(fileIDS,strcat(string(predictedLabel),'-',...
            string(maxClassScore(1,1)),'-',...
            string(bbs(maxClassScore(1,2),1)),'-',...
            string(bbs(maxClassScore(1,2),2)),'-',...
            string(bbs(maxClassScore(1,2),3)),'-',...
            string(bbs(maxClassScore(1,2),4)),'\n'));
        RGB = insertShape(I,'Rectangle',bbs(maxClassScore(1,2),1:4),'LineWidth',2,'Color','green');
        imwrite(RGB , convertStringsToChars( strcat('boundedImages/',string(i),'.jpeg') ) );
        labelsAndScores = [labelsAndScores;predictedLabel,maxClassScore(1,1),bbs(maxClassScore(1,2),1:4)];
        i=i+1;
    % otherwise, declare no categorization
    else
       fprintf(fileIDS,'-1');
       imwrite(I, convertStringsToChars( strcat('boundedImages/',string(i),'.jpeg') ) );
       labelsAndScores = [labelsAndScores;-1,-1,-1,-1,-1,-1];
       i=i+1;
    end
    
end



