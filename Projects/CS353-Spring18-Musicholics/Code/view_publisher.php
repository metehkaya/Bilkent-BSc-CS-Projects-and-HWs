<?php
include("session.php");
$uid = mysqli_real_escape_string($db,$_SESSION['login_id']);
$query = "SELECT * FROM user WHERE user_id = {$uid} ";
$result = mysqli_query($db, $query);
$user_array = mysqli_fetch_array($result,MYSQLI_ASSOC);
$membership_type = $user_array['membership_type'];


$publisher_id = $_GET['publisher_id'];
$query2 = "SELECT * FROM publisher WHERE publisher_id = {$publisher_id} ";
$result2 = mysqli_query( $db, $query2);
	
if($result2 === FALSE){
	echo "<script type=\"text/javascript\"> alert(\"There is no publisher to show!\"); </script>";
}
else{
	$publisher_array = mysqli_fetch_array($result2, MYSQLI_ASSOC);
	$publisher_name = $publisher_array['publisher_name'];
	$publisher_country = $publisher_array['country'];
	$publisher_city = $publisher_array['city'];
}
?>


	<!DOCTYPE html>
	<html lang="en">
	<head>
		<title>Musicholics - View Track</title>
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

		<div class="container">
    
			<div align="left" class="col-md-6 col-md-offset-3"></div>

			<div class="container">
				<div class = "container" align = "center">
					<h2>
						<?php echo $publisher_name; ?>
					</h2>
				</div>
				<br/>
				<div class = "container" align = "center">
					<p> Country: <?php echo $publisher_country;?> </p>
					<p> City: <?php echo $publisher_city;?></p>
				</div>

			</div>
		</div>
		<div class = "container" align = "center"> 

			<table class = "table table-hover" style="width:100%">
			  <tr>
			    <th>Album Name</th>
			    <th>Album Type</th> 
			    <th>Publish Date</th>
			  </tr>
			  <?php
			  $query_album = "SELECT album_name, album_type, published_date, album_id FROM Album WHERE Album.publisher_id = {$publisher_id} ORDER BY published_date";
			  $result = mysqli_query($db, $query_album);
			  
			  while ($row = mysqli_fetch_array($result, MYSQLI_NUM)) {
			      $a_id = $row[3];
			      echo "<tr onclick = \"document.location = 'view_album.php?album_id={$a_id}' \">";
			      echo "<td>" . $row[0] . "</td>";
			      echo "<td>" . $row[1] . "</td>";
			      echo "<td>" . $row[2] . "</td>";
			      echo "</tr>";
			  }
			  ?>
			</table>
		</div>  


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