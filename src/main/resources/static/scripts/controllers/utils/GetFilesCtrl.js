app.controller('GetFilesController', function($scope, $http,ApiHandler) {

    $scope.allFiles = [];
    $scope.showTable = false;

    $scope.getAllFiles = function() {

        // REST URL:
        var url = "utils/upload/getAllFiles";


        ApiHandler.callGet(url,(response)=>{
            $scope.showTable = true;
            $scope.allFiles = response.datalist;
        },(error)=>{
            alert("Error: " + error.data);
        },true);

    }
});