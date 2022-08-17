app.controller('productEditCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.category = $rootScope.Category;
    $scope.colors = [];
    $scope.sizes = [];
    $scope.productSizes=[];
    $scope.productColors=[];

    $scope.newFeatuer= {};
    $scope.data.features = [];
    $scope.featuresList = [];

    $scope.EnableFlage = false;

    $scope.exsitsFlage = false;

    $scope.colorCheckBoxLabel = "#dedede"

    $scope.EditData=()=>{


        if($rootScope.uploadFile === null || $rootScope.uploadFile === undefined)
            $rootScope.uploadFile = $scope.data.image;

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

        $scope.data.colors = [];
        for(let c = 0 ; c < $scope.colors.length;c++)
        {
            let color = $scope.colors[c];
            let color_checkBox = document.getElementById("color_"+color.id);
            if(color_checkBox.checked)
                $scope.data.colors.push(color.id);

        }
        $scope.data.sizes = [];
        for(let s = 0 ; s < $scope.sizes.length;s++)
        {
            let size = $scope.sizes[s];
            let color_checkBox = document.getElementById("Size_"+size.id);
            if(color_checkBox.checked)
                $scope.data.sizes.push(size.id);

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

    $scope.GetData=()=>{
        ApiHandler.callGet('product/info/'+$scope.id,(response)=>{
                $scope.data = response.datalist[0];
                $scope.exsitsFlage = $scope.data.exists;
                $scope.fillFeatures();
                $scope.getSelectedSize();
                $scope.getSelectedcolor();

            },(error)=>{}
            ,true)
    }
    $scope.GetData();

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

    $scope.getSelectedSize=()=>{
        for (let i = 0 ; i < $scope.data.sizes.length;i++)
            $scope.productSizes.push($scope.data.sizes[i]);
    }
    $scope.getSelectedcolor=()=>{
        for (let i = 0 ; i < $scope.data.colors.length;i++)
            $scope.productColors.push($scope.data.colors[i]);
    }

    $scope.addFeatuers=()=>{
        ApiHandler.callPost("features/",$scope.newFeatuer,(response)=>{
            $scope.data.features.push(response.datalist[0].id);
            $scope.featuresList.push(response.datalist[0]);
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

                ApiHandler.callDelete("product/feature/"+ $scope.id+"/"+id,(response)=>{

                },(err)=>{

                },true)

                ApiHandler.callDelete("features/"+id,(response)=>{
                    for (let i=0 ;i < $scope.data.features.length;i++){
                        if($scope.data.features[i]==id){

                            $scope.data.features.splice(i,1);
                            break;
                        }
                    }
                    for (let i=0 ;i < $scope.featuresList.length ;i++){
                        if($scope.featuresList[i].id ==id){
                            $scope.featuresList.splice(i,1);
                            break;
                        }
                    }
                },(error)=>{},true)

            }
        })
    }

    $scope.fillFeatures = () =>{
        for (let i=0 ;i < $scope.data.featuresDataList.length;i++){
            $scope.featuresList.push($scope.data.featuresDataList[i]);
        }
    }

    $scope.changeEnableFlag= ()=>{
        if($scope.EnableFlage)
            $scope.EnableFlage = false;
        else
            $scope.EnableFlage = true;
    }

    $scope.IsSizeSelected= (SizeId)=>{
        for(let i = 0 ; i<$scope.productSizes.length ; i++) {
            let ch =  $scope.productSizes[i]
            if (SizeId == ch)
                return true;
        }
        return false;
    }
    $scope.IsColorSelected= (ColerId)=>{
        for(let i = 0 ; i<$scope.productColors.length ; i++) {
            let ch =  $scope.productColors[i]
            if (ColerId == ch) {
                for(let c = 0 ;c < $scope.colors.length ; c++)
                    if($scope.colors[c].id == ColerId){
                        $scope.val =$scope.colors[c].value;
                        $scope.colorCheckBoxLabel = $scope.val;
                     }
                return true;
            }
            else
            {
                $scope.colorCheckBoxLabel = "#dedede"
            }
        }
        return false;
    }

    $scope.ColorChangeManager=()=>{
        for(let colorId = 0; colorId < $scope.colors.length;colorId++)
        {
            let color = $scope.colors[colorId]
            let color_checkBox = document.getElementById("color_"+color.id)
            let color_Label = document.getElementById("colorLabel"+color.id)

            if (color_checkBox.checked)
                color_Label.style.background = color.value;
            else
                color_Label.style.background = "#dedede"
        }
    }
});