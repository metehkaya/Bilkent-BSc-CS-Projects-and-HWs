model=load('modelBsds'); model=model.model;
model.opts.multiscale=0; model.opts.sharpen=2; model.opts.nThreads=4;
opts = edgeBoxes;
opts.alpha = .65;     % step size of sliding window search
opts.beta  = .75;     % nms threshold for object proposals
opts.minScore = 0;  % min score of boxes to detect
opts.maxBoxes = 50;  % max number of boxes to detect

i = 0;

mkdir('boundedImages3');

while i<=99
    
    % get the edge boxes
    nameStr = strcat('test/images/',string(i),'.JPEG');
    nameStr = convertStringsToChars(nameStr);
    RGB = imread(nameStr);
    bbs = edgeBoxes(RGB,model,opts);
    
    % look for all windows
    j = 1;
    while j<=50
        RGB = insertShape(RGB,'Rectangle',bbs(j,1:4),'LineWidth',2,'Color',[rand*255 rand*255 rand*255]);
        j=j+1;
    end
    
    % save the image with all windows
    imwrite(RGB ,convertStringsToChars( strcat('boundedImages3/',string(i),'.jpeg') ) );
    
    i=i+1;
    
end