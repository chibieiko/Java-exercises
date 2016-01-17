<?php
// Details hidden for security
$servername = "XXX.tamk.fi";
$username = "XXX";
$password = "XXX";
$dbname = "XXX";

// Creates connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Checks connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

print "ROCK-PAPER-SCISSORS LISTINGS";
echo "<br>";
echo "<br>";
print "Multiplayer Scores:";
echo "<br>";
echo "<br>";

// Listings of rock-paper-scissors multiplayer game
$sql = "SELECT Name, Wins, Losses FROM Multiplayer_scores ORDER BY Wins DESC";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo $row["Name"]. ":  Wins " . $row["Wins"]. ",  Losses " . $row["Losses"]. "<br>";
    }
} else {
    echo "0 results";
}

echo "<br>";
print "Single Player Scores:";
echo "<br>";
echo "<br>";

// Listings of rock-paper-scissors singleplayer game
$sql = "SELECT Name, BestScore FROM Highscore ORDER BY BestScore DESC";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo $row["Name"]. ":  Score " . $row["BestScore"]. "<br>";
    }
} else {
    echo "0 results";
}

// closes connection
$conn->close();
?>