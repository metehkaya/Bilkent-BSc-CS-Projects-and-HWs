% function that cals the score
function [label1,score1] = predictImage(svmModel,matName)
    temp = load(matName);
    newX = cell2mat(struct2cell(temp))/norm(cell2mat(struct2cell(temp)));
    [label,score] = predict(svmModel,newX);
    label1 = label;
    score1 = score;
end