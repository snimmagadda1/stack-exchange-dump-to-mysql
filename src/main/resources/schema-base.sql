-- noinspection SqlNoDataSourceInspectionForFile

use
stacke;

-- Do i need this
SET
@@SESSION.SQL_MODE='';

create table badges
(
    Id           INT NOT NULL PRIMARY KEY,
    UserId       INT,
    Name         VARCHAR(50),
    CreationDate DATETIME
) CHARACTER SET = utf8;

CREATE TABLE comments
(
    Id           INT NOT NULL PRIMARY KEY,
    PostId       INT NOT NULL,
    Score        INT NOT NULL DEFAULT 0,
    Text         TEXT,
    CreationDate DATETIME,
    UserId       INT NOT NULL
) CHARACTER SET = utf8;

CREATE TABLE post_history
(
    Id                INT      NOT NULL PRIMARY KEY,
    PostHistoryTypeId SMALLINT NOT NULL,
    PostId            INT      NOT NULL,
    RevisionGUID      VARCHAR(36),
    CreationDate      DATETIME,
    UserId            INT      NOT NULL,
    Text              TEXT
) CHARACTER SET = utf8;

CREATE TABLE posts
(
    Id               INT NOT NULL PRIMARY KEY,
    PostTypeId       SMALLINT,
    AcceptedAnswerId INT,
    ParentId         INT,
    Score            INT NULL,
    ViewCount        INT NULL,
    Body             mediumtext NULL,
    OwnerUserId      INT,
    LastEditorUserId INT,
    LastEditDate     DATETIME,
    LastActivityDate DATETIME,
    Title            varchar(256),
    Tags             VARCHAR(256),
    AnswerCount      INT DEFAULT 0,
    CommentCount     INT DEFAULT 0,
    FavoriteCount    INT DEFAULT 0,
    CreationDate     DATETIME
) CHARACTER SET = utf8;

CREATE TABLE users
(
    Id             INT NOT NULL PRIMARY KEY,
    Reputation     INT NOT NULL,
    CreationDate   DATETIME,
    DisplayName    VARCHAR(50) NULL,
    LastAccessDate DATETIME,
    Views          INT DEFAULT 0,
    WebsiteUrl     VARCHAR(256) NULL,
    Location       VARCHAR(256) NULL,
    AboutMe        TEXT NULL,
    Age            INT,
    UpVotes        INT,
    DownVotes      INT,
    EmailHash      VARCHAR(32)
) CHARACTER SET = utf8;

CREATE TABLE votes
(
    Id           INT NOT NULL PRIMARY KEY,
    PostId       INT NOT NULL,
    VoteTypeId   SMALLINT,
    CreationDate DATETIME
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