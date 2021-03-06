-- noinspection SqlNoDataSourceInspectionForFile

-- Do i need this
SET
@@SESSION.SQL_MODE='';

create table badges
(
    Id         INT NOT NULL PRIMARY KEY,
    UserId     INT,
    Name       VARCHAR(50),
    Date       DATETIME,
    Class      INT,
    BadgeClass INT,
    TagBased   TINYINT
) CHARACTER SET = utf8;

CREATE TABLE comments
(
    Id              INT NOT NULL PRIMARY KEY,
    PostId          INT,
    Score           INT DEFAULT 0,
    Text            TEXT,
    CreationDate    DATETIME,
    UserDisplayName VARCHAR(50),
    UserId          INT,
    ContentLicense  VARCHAR(20)
) CHARACTER SET = utf8;

CREATE TABLE post_history
(
    Id                INT NOT NULL PRIMARY KEY,
    PostHistoryTypeId SMALLINT,
    PostId            INT,
    RevisionGUID      VARCHAR(36),
    CreationDate      DATETIME,
    UserId            INT,
    UserDisplayName   VARCHAR(36),
    Comment           TEXT,
    CloseReasonTypes  SMALLINT,
    PostNoticeId      INT,
    Text              TEXT,
    ContentLicense    VARCHAR(20)
) CHARACTER SET = utf8;

CREATE TABLE posts
(
    Id                    INT NOT NULL PRIMARY KEY,
    PostTypeId            SMALLINT,
    AcceptedAnswerId      INT,
    ParentId              INT,
    Score                 INT NULL,
    ViewCount             INT NULL,
    Body                  mediumtext NULL,
    OwnerUserId           INT,
    OwnerDisplayName      VARCHAR(50),
    LastEditorUserId      INT,
    LastEditorDisplayName VARCHAR(50),
    LastEditDate          DATETIME,
    LastActivityDate      DATETIME,
    CommunityOwnedDate    DATETIME,
    ClosedDate            DATETIME,
    Title                 varchar(256),
    Tags                  VARCHAR(256),
    AnswerCount           INT DEFAULT 0,
    CommentCount          INT DEFAULT 0,
    FavoriteCount         INT DEFAULT 0,
    CreationDate          DATETIME,
    ContentLicense        VARCHAR(20)
) CHARACTER SET = utf8;

CREATE TABLE users
(
    Id               INT NOT NULL PRIMARY KEY,
    Reputation       INT NOT NULL,
    CreationDate     DATETIME,
    DisplayName      VARCHAR(50) NULL,
    LastAccessedDate DATETIME,
    Views            INT DEFAULT 0,
    WebsiteUrl       VARCHAR(256) NULL,
    Location         VARCHAR(256) NULL,
    AboutMe          TEXT NULL,
    Age              INT,
    UpVotes          INT,
    DownVotes        INT,
    ProfileImageUrl  VARCHAR(256) NULL,
    EmailHash        VARCHAR(32),
    AccountId        INT
) CHARACTER SET = utf8;

CREATE TABLE votes
(
    Id           INT NOT NULL PRIMARY KEY,
    PostId       INT,
    VoteTypeId   SMALLINT,
    UserId       INT,
    CreationDate DATETIME,
    BountyAmount INT
) CHARACTER SET = utf8;

-- Use indices for ease of loading...
-- TODO: Best indices to make
create
index badges_idx_1 on badges(UserId);
create
index comments_idx_1 on comments(PostId);
create
index comments_idx_2 on comments(UserId);
create
index post_history_idx_1 on post_history(PostId);
create
index post_history_idx_2 on post_history(UserId);
create
index posts_idx_1 on posts(AcceptedAnswerId);
create
index posts_idx_2 on posts(ParentId);
create
index posts_idx_3 on posts(OwnerUserId);
create
index posts_idx_4 on posts(LastEditorUserId);
create
index votes_idx_1 on votes(PostId);