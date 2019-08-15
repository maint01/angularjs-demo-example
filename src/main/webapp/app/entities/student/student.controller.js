(function(){
	'use strict'
	
	angular.module('shopApp').controller('StudentController', StudentController);
	
	StudentController.$inject = ['$scope', '$http', 'StudentService'];
	function  StudentController($scope, $http, StudentService){
		var vm = this;
		
		vm.key = null;
		
		vm.search = search;
		vm.deleteStudent = deleteStudent;
		
		vm.studentList = [];
		
		loadAllStudent();
		function loadAllStudent(){
				StudentService.query(onSuccess, onError);
				function onSuccess(data){
					console.log(data);
					vm.studentList = data;
				}
		}
		
		function onError(error){
			console.log(error.message);
		}
		
		function search(){
			StudentService.findByName({key: vm.key}, function(data){
				vm.studentList = data; 
			}, function(error){
				console.log(error);
			});
		}
		
		
		function deleteStudent(idStudent){
			var cfm = confirm("Do you want delete this student?");
			if(cfm){
				StudentService.delete({id: idStudent}, function(data){
					alert("Delete success student has identity: " + id);
					loadAllStudent();
				}, function(error){
					console.log(error);
				});
			}
		}
		
		vm.download = function(){
			
			/*StudentService.download(function(data){
				console.log(data);
				var text = 'Nguyen Thanh Mai';
				var blob = new Blob([text], {type: 'plain/text', endings: 'native'});
				var url = URL.createObjectURL(blob);
				saveAs(blob, 'demo.txt')
				
			}, function(error){
				console.log(error);
			});*/
			//window.open('/api/batch_template.xlsx');
			getFile();
			
		};
		
		/*function saveAs(data, fileName){
			var a = document.createElement("a");
		    document.body.appendChild(a);
		    a.style = "display: none";
		    var blob = new Blob([data], {type: "octet/stream"}),
		        url = window.URL.createObjectURL(blob);
		    console.log(blob);
		    a.href = url;
		    a.download = fileName;
		    a.click();
		    window.URL.revokeObjectURL(url);
		}
		
		
		
		function base64ToArrayBuffer(base64) {
		    var binaryString =  window.atob(base64);
		    var binaryLen = binaryString.length;
		    var bytes = new Uint8Array(binaryLen);
		    for (var i = 0; i < binaryLen; i++)        {
		        var ascii = binaryString.charCodeAt(i);
		        bytes[i] = ascii;
		    }
		    return bytes;
		}
//
		var saveByteArray = (function () {
		    var a = document.createElement("a");
		    document.body.appendChild(a);
		    a.style = "display: none";
		    return function (data, name) {
		        var blob = new Blob(data, {type: "octet/stream"}),
		            url = window.URL.createObjectURL(blob);
		        console.log(blob);
		        a.href = url;
		        a.download = name;
		        a.click();
		        window.URL.revokeObjectURL(url);
		    };
		}());
//
*///
		//getFile();
		function getFile(){
			StudentService.getFile(function(data){
				/*console.log(data);
				var sampleBytes = base64ToArrayBuffer(data.bytes);
				saveByteArray([sampleBytes], data.name);*/
				window.open('data:' + data.contentType + ';base64,' + data.bytes, data.name);
			}, function(error){
				console.log(error);
			});
		}
	}
})();