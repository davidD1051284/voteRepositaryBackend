DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_create_vote`(
    IN p_vote_name VARCHAR(255),
    OUT p_vote_id BIGINT
)
BEGIN

    INSERT INTO vote(
        vote_name,
        create_time
    )
    VALUES(
        p_vote_name,
        NOW()
    );

    SET p_vote_id = LAST_INSERT_ID();

END$$

-- =========================
-- 1️⃣ 建立投票
-- =========================
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_create_vote_option`(
    IN p_vote_id BIGINT,
    IN p_option_name VARCHAR(255)
)
BEGIN
    INSERT INTO vote_option(
        option_name,
        total_count,
        vote_id
    )
    VALUES(
        p_option_name,
        0,
        p_vote_id
    );
END$$


-- =========================
-- 2️⃣ 刪除投票（完整版）
-- =========================
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_vote`(
    IN p_vote_id BIGINT
)
BEGIN

    DELETE vro
    FROM vote_record_option vro
    JOIN vote_record vr ON vro.vote_record_id = vr.record_id
    WHERE vr.vote_id = p_vote_id;

    DELETE FROM vote_record
    WHERE vote_id = p_vote_id;

    DELETE FROM vote_option
    WHERE vote_id = p_vote_id;

    DELETE FROM vote
    WHERE id = p_vote_id;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_get_all_votes`()
BEGIN
    SELECT *
    FROM vote
    ORDER BY create_time DESC;
END

-- =========================
-- 3️⃣ 查 vote
-- =========================
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_get_vote_by_id`(
    IN p_vote_id BIGINT
)
BEGIN
    SELECT *
    FROM vote
    WHERE id = p_vote_id;
END$$


-- =========================
-- 4️⃣ 是否投過票
-- =========================
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_has_voted`(
    IN p_user_id BIGINT,
    IN p_vote_id BIGINT
)
BEGIN
    SELECT COUNT(*) AS cnt
    FROM vote_record
    WHERE user_id = p_user_id
      AND vote_id = p_vote_id;
END$$


-- =========================
-- 5️⃣ 投票主紀錄
-- =========================
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_vote`(
    IN p_user_id BIGINT,
    IN p_vote_id BIGINT,
    IN p_user_name VARCHAR(45)
)
BEGIN
    INSERT INTO vote_record(
        user_id,
        vote_id,
        user_name
    )
    VALUES(
        p_user_id,
        p_vote_id,
        p_user_name
    );

    SELECT LAST_INSERT_ID() AS record_id;
END$$


-- =========================
-- 6️⃣ 投票選項 + 計數 +1
-- =========================
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_vote_option`(
    IN p_record_id BIGINT,
    IN p_option_id BIGINT
)
BEGIN
    INSERT INTO vote_record_option(
        vote_record_id,
        option_id,
        vote_time
    )
    VALUES(
        p_record_id,
        p_option_id,
        NOW()
    );

    UPDATE vote_option
    SET total_count = total_count + 1
    WHERE option_id = p_option_id;
END$$

DELIMITER ;