DROP TABLE IF EXISTS "sys_user";

CREATE TABLE "sys_user"
(
  id text NOT NULL, -- 主键ID
  
  username text, -- 用户名
  password text, -- 密码
  
  create_user text, -- 创建用户
  create_time timestamp, -- 创建时间
  
  update_user text, -- 更新用户
  update_time timestamp,--更新时间
  
  deleted integer,--逻辑删除标识
  
  CONSTRAINT id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "sys_user"
  OWNER TO mypostgres;
COMMENT ON COLUMN "sys_user".id IS '主键ID';
COMMENT ON COLUMN "sys_user".createUser IS '创建用户';
COMMENT ON COLUMN "sys_user".createTime IS '创建时间';
COMMENT ON COLUMN "sys_user".updateUser IS '更新用户';
COMMENT ON COLUMN "sys_user".updateTime IS '更新时间';
COMMENT ON COLUMN "sys_user".deleted IS '逻辑删除标识';

