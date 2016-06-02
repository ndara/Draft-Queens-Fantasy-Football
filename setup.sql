DROP TABLE IF EXISTS Rush;
DROP TABLE IF EXISTS Td;
DROP TABLE IF EXISTS Pass;
DROP TABLE IF EXISTS Play;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS Team;
DROP TABLE IF EXISTS Leaderboard;

CREATE TABLE Game
(
gid INT,
wk INT,
v CHAR(3),
h CHAR(3),
PRIMARY KEY(gid)
);

CREATE TABLE Play
(
gid INT REFERENCES Game(gid),
pid INT,
type CHAR(4),
pts INT,
PRIMARY KEY (pid)
);

CREATE TABLE Player
(
player CHAR(7),
fname VARCHAR(64),
lname VARCHAR(64),
pname VARCHAR(64),
pos1 CHAR(5),
height INT,
weight INT,
col VARCHAR(64),
start INT,
cteam CHAR(5),
jnum INT,
dcp INT,
PRIMARY KEY(player)
);

CREATE TABLE Rush
(
pid INT REFERENCES Play(pid),
bc CHAR(7) REFERENCES Player(player),
yds INT,
PRIMARY KEY(pid)
);

CREATE TABLE Td
(
pid INT REFERENCES Play(pid),
yds INT,
player CHAR(7) REFERENCES Player(player),
type CHAR(4),
PRIMARY KEY(pid)
);

CREATE TABLE Pass
(
pid INT REFERENCES Play(pid),
psr CHAR(7) REFERENCES Player(player),
trg CHAR(7) REFERENCES Player(player),
yds INT,
comp INT,
PRIMARY KEY(pid)
);

CREATE TABLE Team
(
id INT,
name VARCHAR(32),
score FLOAT,
QB CHAR(7) REFERENCES Player(player),
RB1 CHAR(7) REFERENCES Player(player),
RB2 CHAR(7) REFERENCES	Player(player),
WR1 CHAR(7) REFERENCES Player(player),
WR2 CHAR(7) REFERENCES Player(player),
WR3 CHAR(7) REFERENCES Player(player),
TE CHAR(7) REFERENCES Player(player),
elim BOOLEAN DEFAULT FALSE
);

CREATE TABLE Leaderboard (
name VARCHAR(32),
score FLOAT,
QB CHAR(7) REFERENCES Player(player),
RB1 CHAR(7) REFERENCES Player(player),
RB2 CHAR(7) REFERENCES	Player(player),
WR1 CHAR(7) REFERENCES Player(player),
WR2 CHAR(7) REFERENCES Player(player),
WR3 CHAR(7) REFERENCES Player(player),
TE CHAR(7) REFERENCES Player(player)
);






