-- Nishanth Dara
-- ndara@calpoly.edu

-- Calculates the average score for a given WR or TE
SELECT AVG(Y.pts)
FROM (
   SELECT
      G.wk, 
      (SUM(S.yds) / 10) + COALESCE(X.tds, 0) AS pts
   FROM
      Play P
      JOIN Game G ON G.gid = P.gid
      JOIN Pass S ON P.pid = S.pid
      JOIN Player L ON S.trg = L.player
      LEFT JOIN (
         SELECT
            COUNT(*) * 6 AS tds,
            L.player,
            wk
         FROM
            Play P
            JOIN Game G ON G.gid = P.gid
            JOIN Pass S ON S.pid = P.pid
            JOIN Player L ON S.trg = L.player
            JOIN Td T ON P.pid = T.pid
         WHERE
            P.type = 'PASS'
            AND L.player = 'RG-2200'
         GROUP BY wk
      ) X ON X.player = L.player AND X.wk = G.wk
   WHERE
      P.type = 'PASS'
      AND L.player = 'RG-2200'
   GROUP BY G.wk
) Y;


-- Calculates the average score for a given RB
SELECT AVG(Y.pts)
FROM (
   SELECT
      G.wk, 
      (SUM(R.yds) / 10) + COALESCE(X.tds, 0) AS pts
   FROM
      Play P
      JOIN Game G ON G.gid = P.gid
      JOIN Rush R ON P.pid = R.pid
      JOIN Player L ON R.bc = L.player
      LEFT JOIN (
         SELECT
            COUNT(*) * 6 AS tds,
            L.player,
            wk
         FROM
            Play P
            JOIN Game G ON G.gid = P.gid
            JOIN Rush R ON R.pid = P.pid
            JOIN Player L ON R.bc = L.player
            JOIN Td T ON P.pid = T.pid
         WHERE
            P.type = 'RUSH'
            AND L.player = 'AP-0700'
         GROUP BY wk
      ) X ON X.player = L.player AND X.wk = G.wk
   WHERE
      P.type = 'RUSH'
      AND L.player = 'AP-0700'
   GROUP BY G.wk
) Y;

-- Calculates the average score for a given QB
SELECT AVG(Y.pts)
FROM (
   SELECT
      G.wk,
      SUM(yds) / 25 + COALESCE(X.tds, 0) AS pts
   FROM
      Play P
      JOIN Game G ON G.gid = P.gid
      JOIN Pass S ON P.pid = S.pid
      JOIN Player L ON L.player = S.psr
      LEFT JOIN (
         SELECT
            wk,
            COUNT(*) * 4 AS tds,
            L.player
         FROM
            Play P
            JOIN Game G ON G.gid = P.gid
            JOIN Pass S ON S.pid = P.pid
            JOIN Player L ON S.psr = L.player
            JOIN Td T ON P.pid = T.pid
         WHERE
            P.type = 'PASS'
            AND L.player = 'CN-0500'
         GROUP BY wk
      ) X ON X.player = L.player AND X.wk = G.wk
   WHERE
      P.type = 'PASS'
      AND L.player = 'CN-0500'
   GROUP BY G.wk
) Y;


-- Finds the average touchdowns scored per game for a given QB
SELECT AVG(Y.pts)
FROM (
   SELECT
      G.wk,
      COALESCE(X.tds, 0) AS pts
   FROM
      Play P
      JOIN Game G ON G.gid = P.gid
      JOIN Pass S ON P.pid = S.pid
      JOIN Player L ON L.player = S.psr
      LEFT JOIN (
         SELECT
            wk,
            COUNT(*) AS tds,
            L.player
         FROM
            Play P
            JOIN Game G ON G.gid = P.gid
            JOIN Pass S ON S.pid = P.pid
            JOIN Player L ON S.psr = L.player
            JOIN Td T ON P.pid = T.pid
         WHERE
            P.type = 'PASS'
            AND L.player = 'TB-2650'
         GROUP BY wk
      ) X ON X.player = L.player AND X.wk = G.wk
   WHERE
      P.type = 'PASS'
      AND L.player = 'TB-2650'
   GROUP BY G.wk
) Y;



