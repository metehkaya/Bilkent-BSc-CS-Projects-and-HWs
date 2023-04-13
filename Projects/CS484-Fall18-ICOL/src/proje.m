function f = proje()

    a=0;
    featureList = {10};
    features = [];
    
    % get feature vectors of each image for each category
    while a<10
        featureList{a+1} = [];
        b = 0;
        while b<50
            try
                file = fullfile(num2str(a), strcat(num2str(a),num2str(b),'.mat'));
                temp = load(file);
                featureList{a+1}=[featureList{a+1};cell2mat(struct2cell(temp))/norm(cell2mat(struct2cell(temp)))];
            catch
               b=50; 
            end
            b=b+1;
        end
        features = [features;featureList{a+1}];
        a=a+1;
    end
    
    % train each category separately
    
    labels = ones(size(featureList{1},1),1);
    labels = [labels;zeros(size(featureList{2},1)+size(featureList{3},1)+size(featureList{4},1)+size(featureList{5},1)+size(featureList{6},1)+size(featureList{7},1)+size(featureList{8},1)+size(featureList{9},1)+size(featureList{10},1),1)];
    eagleModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    labels = ones(size(featureList{2},1),1);
    labels = [zeros(size(featureList{1},1),1);labels;zeros(size(featureList{3},1)+size(featureList{4},1)+size(featureList{5},1)+size(featureList{6},1)+size(featureList{7},1)+size(featureList{8},1)+size(featureList{9},1)+size(featureList{10},1),1)];
    dogModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    labels = ones(size(featureList{3},1),1);
    labels = [zeros(size(featureList{1},1)+size(featureList{2},1),1);labels;zeros(size(featureList{4},1)+size(featureList{5},1)+size(featureList{6},1)+size(featureList{7},1)+size(featureList{8},1)+size(featureList{9},1)+size(featureList{10},1),1)];
    catModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    labels = ones(size(featureList{4},1),1);
    labels = [zeros(size(featureList{1},1)+size(featureList{2},1)+size(featureList{3},1),1);labels;zeros(size(featureList{5},1)+size(featureList{6},1)+size(featureList{7},1)+size(featureList{8},1)+size(featureList{9},1)+size(featureList{10},1),1)];
    tigerModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    labels = ones(size(featureList{5},1),1);
    labels = [zeros(size(featureList{1},1)+size(featureList{2},1)+size(featureList{3},1)+size(featureList{4},1),1);labels;zeros(size(featureList{6},1)+size(featureList{7},1)+size(featureList{8},1)+size(featureList{9},1)+size(featureList{10},1),1)];
    patrickModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    labels = ones(size(featureList{6},1),1);
    labels = [zeros(size(featureList{1},1)+size(featureList{2},1)+size(featureList{3},1)+size(featureList{4},1)+size(featureList{5},1),1);labels;zeros(size(featureList{7},1)+size(featureList{8},1)+size(featureList{9},1)+size(featureList{10},1),1)];
    zebraModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    labels = ones(size(featureList{7},1),1);
    labels = [zeros(size(featureList{1},1)+size(featureList{2},1)+size(featureList{3},1)+size(featureList{4},1)+size(featureList{5},1)+size(featureList{6},1),1);labels;zeros(size(featureList{8},1)+size(featureList{9},1)+size(featureList{10},1),1)];
    bisonModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    labels = ones(size(featureList{8},1),1);
    labels = [zeros(size(featureList{1},1)+size(featureList{2},1)+size(featureList{3},1)+size(featureList{4},1)+size(featureList{5},1)+size(featureList{6},1)+size(featureList{7},1),1);labels;zeros(size(featureList{9},1)+size(featureList{10},1),1)];
    impalaModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    labels = ones(size(featureList{9},1),1);
    labels = [zeros(size(featureList{1},1)+size(featureList{2},1)+size(featureList{3},1)+size(featureList{4},1)+size(featureList{5},1)+size(featureList{6},1)+size(featureList{7},1)+size(featureList{8},1),1);labels;zeros(size(featureList{10},1),1)];
    chimpModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    labels = ones(size(featureList{10},1),1);
    labels = [zeros(size(featureList{1},1)+size(featureList{2},1)+size(featureList{3},1)+size(featureList{4},1)+size(featureList{5},1)+size(featureList{6},1)+size(featureList{7},1)+size(featureList{8},1)+size(featureList{9},1),1);labels];
    elephantModel = fitcsvm(features,labels,'BoxConstraint',1000,'KernelFunction','linear','ClassNames',[0 1],'Standardize',true);
    
    f = {eagleModel;dogModel;catModel;tigerModel;patrickModel;zebraModel;bisonModel;impalaModel;...
        chimpModel;elephantModel};
    
end

