$(document).ready( function(){
	$path = location.pathname
	if($path === '/') {
		$( ".control-display-register" ).show()
	} 
	else if($path === '/register/index') {
		$( ".control-display-list" ).show()
	}
	
});
