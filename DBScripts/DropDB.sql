DROP SEQUENCE BOOK_SEQ;
DROP SEQUENCE KEYWORDS_SEQ;
DROP SEQUENCE KEYWORDS_BOOK_SEQ;
DROP SEQUENCE BOOK_COPY_SEQ;
DROP SEQUENCE AUTHOR_SEQ;
DROP SEQUENCE MESSAGE_SEQ;
DROP SEQUENCE USERS_SEQ;
DROP SEQUENCE COMMENT_SEQ;
DROP SEQUENCE PUBLISHER_SEQ;
DROP SEQUENCE REQUEST_SEQ;
DROP SEQUENCE AUTHOR_BOOK_SEQ;

DROP VIEW top_users_borrowing;
DROP VIEW top_users_commenting;
DROP VIEW top_users_rejected;
DROP VIEW top_users_suggesting;
DROP VIEW top_users_overtime;
DROP VIEW ALL_ADMINS;
DROP VIEW ALL_USERS;
DROP VIEW ALL_REQUESTS;
DROP VIEW MOST_COMMENTED_BOOKS;
DROP VIEW MOST_CREADED_BOOKS;
DROP VIEW MOST_READED_BOOK_COPIES;
DROP VIEW MOST_SUGGESTED_BOOKS;
DROP VIEW MOST_DELAYED_BOOKS;
DROP VIEW ACTUAL_BOOKS;
DROP VIEW ALL_RECOMMENDED_BOOKS;
DROP VIEW ALL_BOOKS;
DROP VIEW ALL_AVAILABLE_BOOKS;
DROP VIEW ALL_UNPROCESSED_REQUESTS;
DROP VIEW ALL_REJECTED_REQUESTS;
DROP VIEW ALL_USER_COMMENTS;
DROP VIEW ALL_BOOK_COMMENTS;
DROP VIEW ALL_USER_MESSAGES;
DROP VIEW ALL_READ_USER_MESSAGES;
DROP VIEW ALL_UNREAD_USER_MESSAGES;

DROP TABLE "BORROW";
DROP TABLE "REQUEST";
DROP TABLE "AUTHOR_BOOK";
DROP TABLE "BOOK_COPY";
DROP TABLE "KEYWORDS_BOOK";
DROP TABLE "COMMENTS";
DROP TABLE "BOOK";
DROP TABLE "CATEGORY";
DROP TABLE "KEYWORDS";
DROP TABLE "MESSAGE";
DROP TABLE "TYPES";
DROP TABLE "AUTHOR";
DROP TABLE "REJECT_REASON";
DROP TABLE "PUBLISHER";
DROP TABLE "LANGUAGE";
DROP TABLE "USERS";
DROP TABLE USER_ROLE;


