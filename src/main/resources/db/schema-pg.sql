
CREATE TABLE "user" (
	id text NOT NULL, -- 主键ID
	username text NOT NULL, -- 用户账户
	"password" text NOT NULL, -- 用户密码
	salt text NOT NULL, -- 密码盐
	
	create_user text NOT NULL, -- 创建用户
	create_time timestamp NOT NULL, -- 创建时间
	update_user text NOT NULL, -- 更新用户
	update_time timestamp NOT NULL, -- 更新时间
	status int4 NOT NULL, -- 状态标识
	CONSTRAINT id PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN "user".id IS '主键ID';
COMMENT ON COLUMN "user".username IS '用户账户';
COMMENT ON COLUMN "user"."password" IS '用户密码';
COMMENT ON COLUMN "user".salt IS '密码盐';

COMMENT ON COLUMN "user".create_user IS '创建用户';
COMMENT ON COLUMN "user".create_time IS '创建时间';
COMMENT ON COLUMN "user".update_user IS '更新用户';
COMMENT ON COLUMN "user".update_time IS '更新时间';
COMMENT ON COLUMN "user".status IS '逻辑删除标识';


