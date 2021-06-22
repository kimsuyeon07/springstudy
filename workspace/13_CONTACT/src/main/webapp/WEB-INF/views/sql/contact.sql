CREATE TABLE CONTACT
(
    NO NUMBER PRIMARY KEY,
    NAME VARCHAR2(32),
    TEL VARCHAR2(100),
    ADDR VARCHAR2(100),
    EMAIL VARCHAR2(100),
    NOTE VARCHAR2(4000)
);

CREATE SEQUENCE CONTACT_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE;

select * from CONTACT;