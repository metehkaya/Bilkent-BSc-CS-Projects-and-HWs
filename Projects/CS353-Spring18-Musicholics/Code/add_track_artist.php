<?php
include("session.php");
$uploaddir = getcwd().'/images/';
$uid = mysqli_real_escape_string($db, $_SESSION['login_id']);
$query1 = "SELECT * FROM user WHERE user_id = {$uid} ";
$result1 = mysqli_query($db, $query1);
$user_array = mysqli_fetch_array($result1, MYSQLI_ASSOC);
    		
?>

<!DOCTYPE html>
<html lang="en">

<head>
	<title>Musicholics - Own Playlists</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
      
				<li><a href="own_profile.php">Profile</a></li>
				<li><a href="view_playlists.php">Playlist</a></li>
				<li><a href="view_tracks.php">Tracks</a></li>
				<li><a href="friends.php">Friends</a></li>
				<li><a href="message_list.php">Messages</a></li>
				<li><a href="search.php">Search</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="change_general_information.php"><span class="glyphicon glyphicon-user"></span> Settings</a></li>
				<li><a href="logout.php"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container" align="center">
		<h3> Add Track </h3> <br>
		<form method="post" action="" enctype="multipart/form-data">
			<input type="text" name="track_name" value= "" placeholder="Track Name" autofocus> <br><br>
			<input type="time" name="duration" value= "" placeholder= "Duration" autofocus> <br><br>
			<input type="text" name="price" value= "" placeholder="Price" autofocus> <br><br>
			<select name="recording_type">
				<option value="Recording Type">Recording Type</option>
				<option value="Studio">Studio</option>
				<option value="Live">Live</option>
			</select>
			<select name="language">
				<option value="Language">Language</option>
				<option value="Turkish">Turkish</option>
				<option value="English">English</option>
				<option value="German">German</option>
			</select>
			<select name="danceability">
				<option value="Danceability">Danceability</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
			<select name="acousticness">
				<option value="Acousticness">Acousticness</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
			<select name="instrumentalness">
				<option value="Instrumentalness">Instrumentalness</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
			<select name="speechness">
				<option value="Speechness">Speechness</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
			<select name="loudness">
				<option value="Loudness">Loudness</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
			<select name="balance">
				<option value="Balance">Balance</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select><br><br>
			<input id = ""  class="btn btn-success" value = "Add" name = "add" type = "submit"> </button> 
			<input id = ""  class="btn btn-danger" value = "Cancel" name = "cancel" type = "submit"> </button> 
		</form>
	</div>

	<?php
		
	if( isset( $_POST['add'])){
		$track_name = mysqli_real_escape_string( $db, $_POST['track_name']);
		$recording_type = mysqli_real_escape_string( $db, $_POST['recording_type']);
		$danceability = mysqli_real_escape_string( $db, $_POST['danceability']);
		$acousticness = mysqli_real_escape_string( $db, $_POST['acousticness']);
		$instrumentalness = mysqli_real_escape_string( $db, $_POST['instrumentalness']);
		$balance = mysqli_real_escape_string( $db, $_POST['balance']);
		$language = mysqli_real_escape_string( $db, $_POST['language']);
		$duration = mysqli_real_escape_string( $db, $_POST['duration']);
		$speechness = mysqli_real_escape_string( $db, $_POST['speechness']);
		$loudness = mysqli_real_escape_string( $db, $_POST['loudness']);
		$price = mysqli_real_escape_string( $db, $_POST['price']);
		$date_of_addition = date("Y-m-d");
		
		if( $track_name == "")
			echo ' <script type="text/javascript"> alert("Choose track name"); </script>';
		if( $recording_type == "Recording Type")
			echo ' <script type="text/javascript"> alert("Choose recording type"); </script>';
		if( $danceability == "Danceability")
			echo ' <script type="text/javascript"> alert("Choose danceability"); </script>';
		if( $acousticness == "Acousticness")
			echo ' <script type="text/javascript"> alert("Choose acousticness"); </script>';
		if( $instrumentalness == "Instrumentalness")
			echo ' <script type="text/javascript"> alert("Choose instrumentalness"); </script>';
		if( $balance == "Balance")
			echo ' <script type="text/javascript"> alert("Choose balance"); </script>';
		if( $language == "")
			echo ' <script type="text/javascript"> alert("Choose language"); </script>';
		if( $duration == "")
			echo ' <script type="text/javascript"> alert("Choose duration"); </script>';
		if( $speechness == "Speechness")
			echo ' <script type="text/javascript"> alert("Choose speechness"); </script>';
		if( $loudness == "Loudness")
			echo ' <script type="text/javascript"> alert("Choose loudness"); </script>';
		
		
		
		$result = mysqli_query( $db, "INSERT INTO Track ( track_name, recording_type, duration, danceability, acousticness, instrumentalness, speechness, balance, loudness, language, price, date_of_addition, album_id)
			VALUES ( '$track_name', '$recording_type', '$duration', '$danceability', '$acousticness', '$instrumentalness', '$speechness', '$balance', '$loudness', '$language', '$price', '$date_of_addition', ( (SELECT MIN(album_id) FROM Album));") or die( mysqli_error( $db));
		
		if( !$result){
			echo ' <script type="text/javascript"> alert("Error: Could not add"); </script>';
		}
		
	}
		?>

		<style>
			.footer {
				position: fixed;
				left: 0;
				bottom: 0;
				width: 100%;
				text-align: center;
			}
			</style>
			<div class = "footer">

				<?php
				$query = "SELECT L1.track_id FROM listens L1 WHERE L1.user_id = '$uid' AND 
					date = (SELECT max(L2.date) FROM listens L2 WHERE L2.user_id = '$uid') ";
				$result = mysqli_query($db, $query);
				$row = mysqli_fetch_array($result, MYSQLI_NUM);
				$query2 = "SELECT track_name,duration FROM track WHERE track_id = '$row[0]' ";
				$result2 = mysqli_query($db, $query2);
				$track_array = mysqli_fetch_array($result2,MYSQLI_ASSOC);

				$track_name = $track_array['track_name'];
				$duration = $track_array['duration'];
				?>

				<h4> <?php echo $track_name; ?> (<?php echo $duration; ?> ) </h4>
  
				<div class="progress">
					<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="70"
					aria-valuemin="0" aria-valuemax="100" style="width:70%">
					<span class="sr-only"> </span> 
				</div>
			</div>
		</body>

		</html>