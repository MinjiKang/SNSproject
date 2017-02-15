CREATE sequence MEMBER_SEQ_NO 
START WITH 1 
INCREMENT BY 1;

Create Table joinMember (
    MEMBER_NO NUMBER NOT NULL,
    MEMBER_ID VARCHAR(200) NOT NULL,
    MEMBER_PASSWORD VARCHAR(20) NOT NULL,
    MEMBER_NAME VARCHAR(20) NOT NULL,
    MEMBER_BIRTH VARCHAR(30) NOT NULL,
    MEMBER_SEX VARCHAR(10) NOT NULL,
    PASSWORD_Q VARCHAR(50) NOT NULL,
    PASSWORD_A VARCHAR(50) NOT NULL,
    FRIEND_STATUS INT NOT NULL,
    primary key (MEMBER_NO)
)


Create Table FRIENDS (
    USER1 NUMBER NOT NULL,
    USER2 NUMBER NOT NULL,
    FRIEND_STATUS INT NOT NULL,
    FRIEND_TYPE INT NOT NULL
)

CREATE sequence board_SEQ_NO 
START WITH 1 
INCREMENT BY 1; 

Create Table board (
    board_no number not null,
    board_writer VARCHAR(20) NOT NULL,
    board_contents VARCHAR(1000) NOT NULL,
    board_date VARCHAR(20) NOT NULL,
    board_time VARCHAR(6) NOT NULL,
    primary key (board_no)
)

Create Table LikeButton (
    board_no number not null,
    member_no integer NOT NULL,
    like_time VARCHAR(20) NOT NULL,
    like_status number NOT NULL,
    primary key (board_no, member_no)
)