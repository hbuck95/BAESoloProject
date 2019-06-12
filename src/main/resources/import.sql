--Prepopulate tables with this
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (1, 'Norse');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (2, 'Egyptian');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (3, 'Roman');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (4, 'Hindu');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (5, 'Greek');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (6, 'Celtic');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (7, 'Chinese');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (8, 'Japanese');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (9, 'Mayan');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (10, 'Arthurian');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (11, 'Slavic');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (12, 'Polynesian');
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (13, 'Voodoo');

INSERT INTO DAMAGETYPE(damagetype_id, damagetype_name) VALUES (1, 'Magical');
INSERT INTO DAMAGETYPE(damagetype_id, damagetype_name) VALUES (2, 'Physical');

INSERT INTO ROLE(role_id, role_name) VALUES (1, 'Guardian');
INSERT INTO ROLE(role_id, role_name) VALUES (2, 'Hunter');
INSERT INTO ROLE(role_id, role_name) VALUES (3, 'Mage');
INSERT INTO ROLE(role_id, role_name) VALUES (4, 'Assassin');
INSERT INTO ROLE(role_id, role_name) VALUES (5, 'Warrior');

INSERT INTO GAMEMODE(gamemode_id, gamemode_name) VALUES (1, 'Conquest');
INSERT INTO GAMEMODE(gamemode_id, gamemode_name) VALUES (2, 'Joust');
INSERT INTO GAMEMODE(gamemode_id, gamemode_name) VALUES (3, 'Duel');

INSERT INTO CHAMPION(champion_id, champion_name, role_id, pantheon_id, damagetype_id, health, damage) VALUES (1,'Ymir', 1,1,1,550,69);
INSERT INTO CHAMPION(champion_id, champion_name, role_id, pantheon_id, damagetype_id, health, damage) VALUES (2,'Neith', 2,2,2,435,38);
INSERT INTO CHAMPION(champion_id, champion_name, role_id, pantheon_id, damagetype_id, health, damage) VALUES (3,'Discordia', 3,3,1,370,34);

INSERT INTO CHAMPIONGAMEMODESTATS(championgamemodestats_id, banrate, pickrate, winrate, champion_id, gamemode_id) VALUES (1, 0.7, 10.95, 46.36, 1, 1);
INSERT INTO CHAMPIONGAMEMODESTATS(championgamemodestats_id, banrate, pickrate, winrate, champion_id, gamemode_id) VALUES (2, 6.44, 11.56, 44.9, 1, 2);
INSERT INTO CHAMPIONGAMEMODESTATS(championgamemodestats_id, banrate, pickrate, winrate, champion_id, gamemode_id) VALUES (3, 1.97, 4.24, 44.05, 1, 3);

INSERT INTO CHAMPIONGAMEMODESTATS(championgamemodestats_id, banrate, pickrate, winrate, champion_id, gamemode_id) VALUES (4, 0.37, 10.95, 47.04, 2, 1);
INSERT INTO CHAMPIONGAMEMODESTATS(championgamemodestats_id, banrate, pickrate, winrate, champion_id, gamemode_id) VALUES (5, 4.11, 19.04, 47.15, 2, 2);
INSERT INTO CHAMPIONGAMEMODESTATS(championgamemodestats_id, banrate, pickrate, winrate, champion_id, gamemode_id) VALUES (6, 1.03, 3.06, 44.05, 2, 3);

INSERT INTO CHAMPIONGAMEMODESTATS(championgamemodestats_id, banrate, pickrate, winrate, champion_id, gamemode_id) VALUES (7, 0.38, 4.45, 48.47, 3, 1);
INSERT INTO CHAMPIONGAMEMODESTATS(championgamemodestats_id, banrate, pickrate, winrate, champion_id, gamemode_id) VALUES (8, 4.17, 9.3, 51.58, 3, 2);
INSERT INTO CHAMPIONGAMEMODESTATS(championgamemodestats_id, banrate, pickrate, winrate, champion_id, gamemode_id) VALUES (9, 0.26, 0.59, 32.38, 3, 3);
