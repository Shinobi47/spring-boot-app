INSERT INTO USER_PROFILE(UPR_USER_ID, UPR_USER_NAME, UPR_USER_PASSWD, UPR_IS_USER_ACTIVE) values (1, 'Haytam', '$2a$10$tnLhlesgUNv6o5EWEAndhuXQ.alShp0yZ1Zde5xW.IjaukByJxg7W', true);
INSERT INTO USER_PROFILE(UPR_USER_ID, UPR_USER_NAME, UPR_USER_PASSWD, UPR_IS_USER_ACTIVE) values (2, 'user1', '$2a$10$tnLhlesgUNv6o5EWEAndhuXQ.alShp0yZ1Zde5xW.IjaukByJxg7W', true);
INSERT INTO USER_PROFILE(UPR_USER_ID, UPR_USER_NAME, UPR_USER_PASSWD, UPR_IS_USER_ACTIVE) values (3, 'petitL', '$2a$10$tnLhlesgUNv6o5EWEAndhuXQ.alShp0yZ1Zde5xW.IjaukByJxg7W', false); -- MOUAHAHAH

INSERT INTO ROLE(ROLE_ID, ROLE_NAME) VALUES (1, 'ROOT');
INSERT INTO ROLE(ROLE_ID, ROLE_NAME) VALUES (2, 'USER');

INSERT INTO USER_ROLE(USRL_USER_ID, USRL_ROLE_ID) VALUES(1, 1);
INSERT INTO USER_ROLE(USRL_USER_ID, USRL_ROLE_ID) VALUES(2, 2);
INSERT INTO USER_ROLE(USRL_USER_ID, USRL_ROLE_ID) VALUES(3, 2);