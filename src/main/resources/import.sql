--Prepopulate tables with this
INSERT INTO PANTHEON(pantheon_id, pantheon_name) VALUES (1, 'Norse');
INSERT INTO DAMAGETYPE(damagetype_id, damagetype_name) VALUES (1, 'Magical');
INSERT INTO ROLE(role_id, role_name) VALUES (1, 'Guardian');

INSERT INTO CHAMPION(champion_id, champion_name, role_id, pantheon_id, damagetype_id, health, damage) VALUES (1,'Ymir', 1,1,1,550,69);