-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5282
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

REPLACE INTO `block_type` (id,`ref_name`,`description`) VALUES
(1,'text','Plain text'),
(2,'html','HTML5'),
(3,'markdown','Markdown text format');

-- Dumping data for table liamjdavison.block: ~38 rows (approximately)
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
REPLACE INTO `block` (`id`, `ref_name`, `uuid`, `created_on`, `updated_on`, `page`, `group`, `template`, `content`,`type`) VALUES
	(1, 'homePageBlock_1', _binary 0x29D10A27DA394D00B3D4FAFA5E0D79A4, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 1',1),
	(2, 'homePageBlock_2', _binary 0x59EB3FCA8B454F5DA5E8F1C5A32125CD, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 2',1),
	(3, 'homePageBlock_3', _binary 0xA9317A7E88B74DACBAF704CE835FF479, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 3',1),
	(4, 'homePageBlock_4', _binary 0xE3F79F14167F4FFA8C3277321567B0FF, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 4',1),
	(5, 'homePageBlock_5', _binary 0x29156DC665EC40D3A78A6D24E205E5C3, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 5',1),
	(6, 'homePageBlock_6', _binary 0x7470970BBEF94854B8B6C25283D9F193, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 6',1),
	(7, 'homePageBlock_7', _binary 0x02874CE8AEA94963A1459700100A5A28, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 7',1),
	(8, 'homePageBlock_8', _binary 0x301E4C2BBAE24A208819A0A240744CD0, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 8',1),
	(9, 'homePageBlock_9', _binary 0x16E4621B00F647909D36B93D30346242, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 9',1),
	(10, 'homePageBlock_10', _binary 0x820D8E2DDED9403986F40F3A0F5CDF77, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 1, NULL, 1, 'This is block 10',1),
	(11, 'aboutPageBlock_50', _binary 0x0E2EE007689B4131A278A7984052A1FB, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 2, NULL, 2, 'This block contains plain unformatted text.\r\n\r\nOther the weekend I found a number of problems with the input validation code I had written, and I felt that I had to go back to the drawing board and start again. Part of the issue has been my unfamiliarity with with Javascript, and part because I think I was trying to combine too many different behaviours into one action. So I started reading around, looking for examples of how other frameworks handle validation.\r\n',1),
	(12, 'aboutPageBlock_51', _binary 0x4C009B712A7B432DB1B6DCE0425D4DC3, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 2, NULL, 2, '<p>This block contains straightforward HTML.</p>\r\n<p>Here, I have asked <strong>jQuery</strong> to <em>serialize</em> the form data, which turns the form data into a string of query parameters - for instance, <code>name=Liam&amp;age=21</code> - and then <em>POST</em> them to the given URL <code>/ajax/submit</code>. This is easily understood by the controller. It can get a little unwieldy if there are a lot of parameters, and the controller will need to convert the values into the right type (Integers, etc) before validation.</p>',1),
	(13, 'aboutPageBlock_52', _binary 0x359C3DF26D814983BF27D3CD6388552C, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 2, NULL, 2, 'This block contains Markdown.\r\n==================\r\n\r\nAny discussion of user-input in web applications must start with a discussion of _client-side_ verses _server-side_ validation. Of course, the correct answer is to do both. I\'m focusing on the server-side because I think it\'s more important than client-side, a more interesting problem to solve, and keeps me away from nasty Javascript for a little longer.\r\n\r\nLet\'s start with the desired workflow:\r\n* A web form is displayed and the user enters some values\r\n* The user clicks \'Submit\' and an AJAX POST event is fired, sending the contents of the web form to the controller',1),
	(14, 'aboutPageBlock_53', _binary 0x4D13465643EB499D8BD6CF2F8CF16269, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 2, NULL, 2, 'This is block 53',1),
	(15, 'aboutPageBlock_54', _binary 0x74959E84B45841C592499104B950BA5E, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 2, NULL, 2, 'This is block 54',1),
	(16, 'aboutPageBlock_55', _binary 0x8B5CDA5042BA41509EAFCB05FE33130B, '2018-08-05 13:17:49.421000', '2018-08-05 13:17:49.421000', 2, NULL, 2, 'This is block 55',1),
	(17, 'homePageGroupBlock_217', _binary 0xDF5788D66A8444AC927733D31522B000, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 217',1),
	(18, 'homePageGroupBlock_218', _binary 0x3411A481154442FAB6B4E1E8FF0F5E72, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 218',1),
	(19, 'homePageGroupBlock_219', _binary 0x65BDCE540F6C4F9DB3238319CF7C8044, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 219',1),
	(20, 'homePageGroupBlock_220', _binary 0xF68312251DB64629A7684710A2EDD85E, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 220',1),
	(21, 'homePageGroupBlock_221', _binary 0x65462E7749604E318F197B9BE383CCDC, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 221',1),
	(22, 'homePageGroupBlock_222', _binary 0xC07A6A7D8B5345398EAEAA5C92932F90, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 222',1),
	(23, 'homePageGroupBlock_223', _binary 0xCA5ED7BDAE3F47F8BA82C5BF4CFE278E, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 223',1),
	(24, 'homePageGroupBlock_224', _binary 0xB91CADBF9F814595A8535A7DF4B522E7, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 224',1),
	(25, 'homePageGroupBlock_225', _binary 0x84D6DB3DF6514FD7B41152D547F781F5, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 225',1),
	(26, 'homePageGroupBlock_226', _binary 0x07413FCCB998471383DF53BDEA056ED4, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 226',1),
	(27, 'homePageGroupBlock_227', _binary 0xB370C143E438413B93513371DF26EFDD, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 227',1),
	(28, 'homePageGroupBlock_228', _binary 0x3DEC3E6C382546E787544FFC0F50E68C, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 228',1),
	(29, 'homePageGroupBlock_229', _binary 0x83AFDFD3B92D45B79C764D326DDFD56F, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 1, 1, 2, 'This is block 229',1),
	(30, 'aboutPageGroupBlock_5141', _binary 0x43B5B3B042AE4E4B9BD973DB48BB76F8, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 2, 2, 1, 'This is block 5141',1),
	(31, 'aboutPageGroupBlock_5142', _binary 0xAF6CE8D8DB1241AE90B2AC7CF717861D, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 2, 2, 1, 'This is block 5142',1),
	(32, 'aboutPageGroupBlock_5143', _binary 0x441F206E7EBF4FDE8A419631727DDD84, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 2, 2, 1, 'This is block 5143',1),
	(33, 'aboutPageGroupBlock_5144', _binary 0x7337DAB98ED547A998ADE2AF62CB65F0, '2018-08-05 13:17:49.442000', '2018-08-05 13:17:49.442000', 2, 2, 1, 'This is block 5144',1),
	(34, 'aboutPageGroupBlock_5145', _binary 0x813E03360C1B443E89AB88457A65114E, '2018-08-05 13:17:49.443000', '2018-08-05 13:17:49.443000', 2, 2, 1, 'This is block 5145',1),
	(35, 'aboutPageGroupBlock_5146', _binary 0x4E9CEB66F58B4E5188974C0D7B92B07A, '2018-08-05 13:17:49.443000', '2018-08-05 13:17:49.443000', 2, 2, 1, 'This is block 5146',1),
	(36, 'aboutPageGroupBlock_5147', _binary 0x69B67C5579D647519DB3E6449303D8B5, '2018-08-05 13:17:49.443000', '2018-08-05 13:17:49.443000', 2, 2, 1, 'This is block 5147',1),
	(37, 'aboutPageGroupBlock_5148', _binary 0xFB5459F7E5C94A12A599774B88C49A2C, '2018-08-05 13:17:49.443000', '2018-08-05 13:17:49.443000', 2, 2, 1, 'This is block 5148',1),
	(38, 'aboutPageGroupBlock_5149', _binary 0x922E180810A24204BE4E2B263E3F6EA0, '2018-08-05 13:17:49.443000', '2018-08-05 13:17:49.443000', 2, 2, 1, 'This is block 5149',1);
/*!40000 ALTER TABLE `block` ENABLE KEYS */;

-- Dumping data for table liamjdavison.block_group: ~2 rows (approximately)
/*!40000 ALTER TABLE `block_group` DISABLE KEYS */;
REPLACE INTO `block_group` (`id`, `ref_name`, `uuid`, `created_on`, `updated_on`, `page`) VALUES
	(1, 'homePageGroup', _binary 0x5997DEE3E7F84D8C9EA4DD01173A707E, '2018-08-05 13:17:49.396000', '2018-08-05 13:17:49.396000', 1),
	(2, 'aboutPageGroup', _binary 0xD653FB1F5E6B4A078F5A7BD3C991ED23, '2018-08-05 13:17:49.396000', '2018-08-05 13:17:49.396000', 2);
/*!40000 ALTER TABLE `block_group` ENABLE KEYS */;

-- Dumping data for table liamjdavison.block_template: ~2 rows (approximately)
/*!40000 ALTER TABLE `block_template` DISABLE KEYS */;
REPLACE INTO `block_template` (`id`, `ref_name`, `uuid`, `created_on`, `updated_on`, `source`) VALUES
	(1, 'mockBlockTemplate_A', _binary 0x726478D834654577A203C056B751F76A, '2018-08-05 13:17:49.355000', '2018-08-05 13:17:49.355000', 'block template alpha'),
	(2, 'mockBlockTemplate_B', _binary 0xF7F661A712D04795A6106F77CC34AB64, '2018-08-05 13:17:49.355000', '2018-08-05 13:17:49.355000', 'block template beta');
/*!40000 ALTER TABLE `block_template` ENABLE KEYS */;

-- Dumping data for table liamjdavison.page: ~2 rows (approximately)
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
REPLACE INTO `page` (`id`, `ref_name`, `uuid`, `created_on`, `updated_on`, `template`, `title`, `dirty`) VALUES
	(1, 'home', _binary 0xA8538427A95D4198AF4C8FF6F2E64F6A, '2018-08-05 13:17:49.381000', '2018-08-05 13:17:49.381000', 1, 'Home Page', 0),
	(2, 'about', _binary 0xB019D3419DD640C8A1A5BAF47CE015DF, '2018-08-05 13:17:49.381000', '2018-08-05 13:17:49.381000', 2, 'About Me', 0);
/*!40000 ALTER TABLE `page` ENABLE KEYS */;

-- Dumping data for table liamjdavison.page_template: ~1 rows (approximately)
/*!40000 ALTER TABLE `page_template` DISABLE KEYS */;
REPLACE INTO `page_template` (`id`, `ref_name`, `uuid`, `created_on`, `updated_on`, `source`) VALUES
	(1, 'home', _binary 0x4D5BD4787A9D40DCA966A90CE1ECA765, '2018-08-05 13:17:49.307000', '2018-08-05 13:17:49.307000', '<b>The home page template!</b>'),
	(2, 'about', _binary 0x11110000000000000000000000000000, '2018-08-07 20:11:10.000000', '2018-08-07 20:11:12.000000', 'This is the about page template');
/*!40000 ALTER TABLE `page_template` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
