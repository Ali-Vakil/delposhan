// CONTROLLER UPLOAD FILE
app.controller('UploadFileController', function($scope, $http,ApiHandler,$cookies,$rootScope) {

    $scope.uploadResult ="";

    $scope.myForm = {
        description: "",
        files: []
    }

    $scope.doUploadFile = function() {

        var url = "/api/utils/upload/uploadMultiFiles";

        let token = $cookies.get("userToken");

        var data = new FormData();

        data.append("description", $scope.myForm.description);
        data.append("files", $scope.myForm.files);


        var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
                'Content-Type': undefined,
                'Authorization':'Bearer '+token
            }
        }


        $http.post(url, data, config).then(
            // Success
            function(response) {
                $scope.uploadResult =  response.data;
                $rootScope.uploadFile = response.data;
            },
            // Error
            function(response) {
                $scope.uploadResult = response.data;
            });

        // ApiHandler.callPost(url,data,(respons)=>{
        //     $scope.uploadResult =  response.data;
        // },(error)=>{
        //     $scope.uploadResult = error.data;
        // },true
        // ,config);
    };

});