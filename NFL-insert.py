target_file = "NFL-insert.sql"
dataset_path = "/Users/gurjeevan/Documents/school/DatabasesFinalProject/NFL2015SampleData/csv"
srcPlayers = dataset_path + "/PLAYER.csv"
srcRushes = dataset_path + "/RUSH.csv"
srcPasses = dataset_path + "/PASS.csv"
srcGames = dataset_path + "/GAME.csv"
srcPlays = dataset_path + "/PLAY.csv"
srcTds = dataset_path + "/TD.csv"

target = open(target_file,'w')

def addSemi(string):
   temp = ""
   for i in range(0,len(string)-2):
      temp += string[i]
   temp += ';\n\n'
   return temp

# WRITING SQL SCRIPT FOR PLAYERS #
f = open(srcPlayers, 'r')
string = ""
string += "INSERT INTO Player\n   (player, fname, lname, pname, pos1, col, cteam)\nVALUES\n"
f.readline()
for line in f:
   thing = line.strip('\r\n')
   shit = thing.split(',')
   string += "   ('" + shit[0] + "', '" + shit[1] + "', '" + shit[2] + "', '" + shit[3] + "', '" + shit[4] + "', '" + shit[5] + "', '" + shit[6] + "'),\n"
string = addSemi(string)
target.write(string)
f.close()

# WRITING SQL SCRIPT FOR RUSHES # 
f = open(srcRushes, 'r')
string = ""
string += "INSERT INTO Rush\n   (pid, bc, yds)\nVALUES\n"
f.readline()
for line in f:
   thing = line.strip('\r\n')
   shit = thing.split(',')
   string += "   (" + shit[0] + ", '" + shit[1] + "', " + shit[2] + "),\n"
string = addSemi(string)
target.write(string)
f.close()

# WRITING SQL SCRIPT FOR PASSES #
f = open(srcPasses, 'r')
string = ""
string += "INSERT INTO Pass\n   (pid, psr, trg, yds, comp)\nVALUES\n"
f.readline()
for line in f:
   thing = line.strip('\r\n')
   shit = thing.split(',')
   string += "   (" + shit[0] + ", '" + shit[1] + "', '" + shit[2] + "', " + shit[3] + ", " + shit[4] + "),\n"
string = addSemi(string)
target.write(string)
f.close()

# WRITING SQL SCRIPT FOR GAME #
f = open(srcPasses, 'r')
string = ""
string += "INSERT INTO Game\n   (gid, wk, v, h)\nVALUES\n"
f.readline()
for line in f:
   thing = line.strip('\r\n')
   shit = thing.split(',')
   string += "   (" + shit[0] + ", " + shit[1] + ", '" + shit[2] + "', '" + shit[3] + "', " + "),\n"
string = addSemi(string)
target.write(string)
f.close()

# WRITING SQL SCRIPT FOR Play #
f = open(srcPasses, 'r')
string = ""
string += "INSERT INTO Play\n   (gid, pid, type, pts)\nVALUES\n"
f.readline()
for line in f:
   thing = line.strip('\r\n')
   shit = thing.split(',')
   string += "   (" + shit[0] + ", " + shit[1] + ", '" + shit[2] + "', " + shit[3] + ", " + "),\n"
string = addSemi(string)
target.write(string)
f.close()

# WRITING SQL SCRIPT FOR TD #
f = open(srcPasses, 'r')
string = ""
string += "INSERT INTO Td\n   (pid, yds, player, type)\nVALUES\n"
f.readline()
for line in f:
   thing = line.strip('\r\n')
   shit = thing.split(',')
   string += "   (" + shit[0] + ", " + shit[1] + ", '" + shit[2] + "', '" + shit[3] + "', " + "),\n"
string = addSemi(string)
target.write(string)
f.close()

target.close()















