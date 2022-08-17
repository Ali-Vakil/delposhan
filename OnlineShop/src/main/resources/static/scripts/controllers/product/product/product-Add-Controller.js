app.controller('productAddCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {}; // this is an object
    $scope.category = $rootScope.Category;
    $scope.colors = []; // this is an array
    $scope.sizes = [];

    $scope.newFeatuer= {};
    $scope.data.features = [];
    $scope.FeaturesList = [];


    $scope.addData=()=>{

        $scope.data.image = $rootScope.uploadFile;

        $scope.data.category = $scope.category.id;

        if($scope.data.title === undefined ||$scope.data.title ==null || $scope.data.title === "")
        {
            Swal.fire('Pleas Insert Title');
            return;
        }if($scope.data.description === undefined ||$scope.data.description ==null || $scope.data.description === "")
        {
            Swal.fire('Pleas Insert description');
            return;
        }if($scope.data.price === undefined ||$scope.data.price ==null || $scope.data.price === "")
        {
            Swal.fire('Pleas Insert Price');
            return;
        }if($scope.data.enable === undefined ||$scope.data.enable ==null || $scope.data.enable === "")
        {
            Swal.fire('Pleas Choose enable');
            return;
        }if($scope.data.exists === undefined ||$scope.data.exists ==null || $scope.data.exists === "")
        {
            Swal.fire('Pleas Choose exists');
            return;
        }if($scope.data.image === undefined ||$scope.data.image ==null || $scope.data.image === "")
        {
            Swal.fire('Pleas Upload Image');
            return;
        }

        ApiHandler.callPost("product/",$scope.data,(response)=>{
            $scope.changeMenuWithCategory("product-list")
        },(error)={

        },true)
    }

    $scope.changeMenuWithCategory=(template)=>{
        $rootScope.category = $scope.category;
        $scope.changeMenu(template);
    }

    $scope.getColor = ()=>{
        ApiHandler.callGet("color/",(response)=>{
            $scope.colors = response.datalist;
        },(error)=>{

        },true)
    }

    $scope.getColor();

    $scope.getSize =()=>{
        ApiHandler.callGet("size/",(response)=>{
            $scope.sizes = response.datalist;
            },(error)=>{

            },true)
    }

    $scope.getSize();

    $scope.addFeatuers=()=>{
        ApiHandler.callPost("features/",$scope.newFeatuer,(response)=>{
            $scope.data.features.push(response.datalist[0].id);
            $scope.FeaturesList.push(response.datalist[0]);
            $scope.newFeatuer = {};
        },(error)=>{},true)
    }

    $scope.deleteFeatuers=(id)=>{

        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                ApiHandler.callDelete("product/")
                ApiHandler.callDelete("features/"+id,(response)=>{
                    for (let i=0 ;i < $scope.data.features.length;i++){
                        if($scope.data.features[i]==id){
                            $scope.data.features.splice(i,1);
                            break;
                        }
                    }
                    for (let i=0 ;i < $scope.FeaturesList.length ;i++){
                        if($scope.FeaturesList[i].id ==id){
                            $scope.FeaturesList.splice(i,1);
                            break;
                        }
                    }
                },(error)=>{},true)

            }
        })
    }

});