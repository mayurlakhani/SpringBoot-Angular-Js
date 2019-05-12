/**
 * ======================
   manage user controller ::
 * ======================
 */
App.controller("manageUserCtrl",['$scope','RoleService','UserService','$rootScope','fileUpload',function($scope,RoleService,UserService,$rootScope,fileUpload) {

	//### initially fetch all roles::
	
		$scope.init = function() {
		$scope.fetchAllroles();
	};
	
	//File Upload::
	
	/*$scope.uploadFile = function(){
        var file = $scope.uploadfile;
        console.log('file is ' );
        console.dir(uploadfile);
        var uploadUrl = "datumuser/uploadFile";
        fileUpload.uploadFileToUrl(file, uploadUrl);
    };*/
	
	// SAVE USER :
	
		$scope.saveUser = function(user) {
		var formData = new FormData();		
		formData.append('email',user.email);   //(entity,ng-model)
		formData.append('roleId',user.role.id);
		formData.append('gender',user.gender);
		formData.append('validate',user.validate);
		
		if($scope.user.id != undefined)
			formData.append('id',user.id);
			
		UserService.createUser(formData).then(function(data) {
            if(data.status== "true") {
            	$scope.users.push({email:'',roleId:'$scope.user.role',gender:'$scope.user.gender',validate:'$scope.user.validate'});
            	$scope.email='';
            	$scope.roleId='';
            	$scope.gender='';
            	$scope.validate='';
            	
            } else {
            	
            	$scope.status = false;
            }
            $rootScope.fetchAllusers();
            $scope.message = data.data.message;
        })
	};
		
	// fetch all roles:
			$scope.fetchAllroles = function(){
				RoleService.fetchAllroles().then(function(data) {
					$scope.roles=data.data.data.roles; 
 	   })
	};
		 
}])


/**
 * ================
 * Home controller ::
 * ================
 */
App.controller("homeCtrl",['$rootScope', '$scope','RoleService','UserService',function($rootScope, $scope,RoleService,UserService) {
	       
	$scope.users={email:'',roleId:'$scope.user.role',gender:'$scope.user.gender',validate:'$scope.user.validate'};
    
	//initially fetch all data in page :
	        $scope.init = function() {
		    $scope.fetchAllusers();
	    };
	
	//fetch all users :
	    
	        $scope.fetchAllusers = function(){
	        //Clear user from root scope
	        $rootScope.user = '';
 	        UserService.fetchAllUsers().then(function(data) {
            $scope.users=data.data.data.users;
 	        });
	    };

	//fetch order by Email :
	
	    	$scope.fetchOrderByEmailAsc= function(){
	    		UserService.fetchOrderByEmailAsc().then(function(data) {
	    				$scope.users=data.data.data.users;		  
	 	   })
		};
		
	
	//delete by id::
		
	      $scope.deleteUser= function(id){
		  UserService.deleteUser(id).then(function(data) {
 		  if(data.status == "true" ) {
            	  $scope.status = true;
            	  $scope.users={email:'',roleId:'$scope.user.role',gender:'$scope.user.gender',validate:'$scope.user.validate'};
            	  $scope.users.splice(id, 1);
            	  console.log($scope.status);
            } else {
            	
            	  $scope.status = false;	
            }
 		  			
 		  		$scope.fetchAllusers();
        		$scope.message = data.message;
 	       })
	    };
	
	//get user detail::
	    
	       $scope.getUser= function(id){
		   UserService.getUser(id).then(function(data) {
 		   if(data.status == "success") 	{
 			      $scope.status = true;
            } else {
                  $scope.status = false;
            }
 		 
 		 $rootScope.user = data.data.data;
 		 $rootScope.message = data.message;
 	   })
	};
	
	//SEARCH BY KEYWORD ::
	
		    $scope.search = function(keyword){
			var keyword=$scope.keyword;
			$scope.users = [];
			UserService.searchUser(keyword).then(function(data) {
            if(data.data.status == true) {
            	
            	   $scope.users=data.data.data.users;
            } else {
            	
            	   $scope.status = false;
            }
            $scope.message = data.data.message;
        })
	};	
	
	//pagination ::
	
			$scope.users = [];
				for (var i = 0; i < $scope.users.length; i++) {
						$scope.users.push(i);
				}
    
			$scope.rowsPerPage = 3;
    
			$scope.sort = function(keyname){
			$scope.sortKey = keyname;   //set the sortKey to the param passed
			$scope.reverse = !$scope.reverse; //if true make it false and vice versa
		}
    
   // FILTER : order by ::
			
			$scope.orderByMe = function(x) {
				$scope.myOrderBy = x;
		}
}]);

/**
 * ================
 * pagination code :
 * ================
 */

App.filter('paginate', function(Paginator) {
    return function(input, rowsPerPage) {
        if (!input) {
            return input;
        }

        if (rowsPerPage) {
            Paginator.rowsPerPage = rowsPerPage;
        }
        
        Paginator.itemCount = input.length;

        return input.slice(parseInt(Paginator.page * Paginator.rowsPerPage), parseInt((Paginator.page + 1) * Paginator.rowsPerPage + 1) - 1);
    }
}) .filter('forLoop', function() {
    return function(input, start, end) {
        input = new Array(end - start);
        for (var i = 0; start < end; start++, i++) {
            input[i] = start;
        }

        return input;
    }
}).directive('paginator', function factory() {
    return {
        restrict:'E',
        controller: function ($scope, Paginator) {
            $scope.paginator = Paginator;
        },
        templateUrl: 'page.html'
    };
}).directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);