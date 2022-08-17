app.controller("panelCtrl", function ($scope, ApiHandler, $cookies,$rootScope) {

    $scope.template = "";
    $scope.templateName = "";
    $scope.templateGtoup = "";


    $scope.checkAccess = ()=>{
        let token = $cookies.get("userToken")
        if(token == undefined || token == null || token == ""){
            location.href = "/login";
            return;
        }
        $scope.getUserInfo();
    }

    $scope.getUserInfo = () => {
       ApiHandler.callGet('user/getUserInfo',(response)=>{
           $rootScope.userInfo = response.datalist[0];
           $scope.user = $rootScope.userInfo;
       },(err)=>{

       },true)
    }

    $scope.changeMenu=(templateName)=>{
        $scope.templateName = templateName;
        $scope.template = $scope.getMenuPrefix(templateName);
        $scope.templateGtoup = $scope.getTemplateGroup(templateName);
    }
    $scope.getMenuPrefix= (templateName)=>{
        if (templateName.toString().startsWith('nav')) {
            return 'views/site/nav/' + templateName + '.html';
        }
        else if(templateName.toString().startsWith('content')) {
            return 'views/site/content/' + templateName + '.html';
        }else if(templateName.toString().startsWith('slider')) {
            return 'views/site/slider/' + templateName + '.html';
        }else if(templateName.toString().startsWith('blog')) {
                return 'views/site/blog/' + templateName + '.html';
        }else if(templateName.toString().startsWith('user')) {
            return 'views/people/user/' + templateName + '.html';
        }else if(templateName.toString().startsWith('category')) {
            return 'views/product/category/' + templateName + '.html';
        }else if(templateName.toString().startsWith('size')) {
            return 'views/product/size/' + templateName + '.html';
        }else if(templateName.toString().startsWith('color')) {
            return 'views/product/color/' + templateName + '.html';
        }else if(templateName.toString().startsWith('product')) {
            return 'views/product/product/' + templateName + '.html';
        }else if(templateName == "List-Customer") {
            return 'views/people/customers/' + templateName + '.html';
        }else if(templateName == "uploader") {
            return 'views/utils/' + templateName + '.html';
        }
        else if(templateName.toString().startsWith('Customer') ||
            templateName == "InvoiceDetails" ||
            templateName == "CustomerProfile") {
            return 'views/CustomerViews/' + templateName + '.html';
        }
        else {
            return 'views/dashboard.html';
        }
    }
    $scope.getTemplateGroup = (templateName)=>{
        if (templateName.toString().startsWith('nav')) {
            return 'nav';
        }
        else if(templateName.toString().startsWith('content')) {
            return 'content';
        }
        else if(templateName.toString().startsWith('slider')) {
            return 'slider';
        }
        else if(templateName == "uploader") {
            return 'uploader';
        }
        else if(templateName.toString().startsWith('blog')) {
            return 'blog';
        }
        else if(templateName.toString().startsWith('user')) {
            return 'user';
        }
        else if(templateName.toString().startsWith('category') ||
                templateName.toString().startsWith('color')    ||
                templateName.toString().startsWith('size')     ||
                templateName.toString().startsWith('product')
        ){
            return 'product';
        }else if(templateName == "InvoiceDetails" ||
            templateName == "CustomerProfile") {
            return 'CustomerProfile';
        }else if(templateName == "CustomerDashboard") {
            return 'CustomerDashboard';
        }else if(templateName == "List-Customer") {
            return 'Customer';
        }else if(templateName.toString().startsWith('')) {
            return 'dashboard';
        }
    }
    $scope.checkAccess();

    $scope.init= (id)=>{
        if(id == -1){
           $scope.changeMenu("dashboard");
        }
        else
        {
            $rootScope.currentCustomerId = id;
            $scope.changeMenu("CustomerDashboard");
        }

    }

    $scope.Logout = () => {
        $cookies.remove("userToken");
        location.href = "/login";
    }
});