DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `test`()
BEGIN
    select * from oempro_campaigns;
END