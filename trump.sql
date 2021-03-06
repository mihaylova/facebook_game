-- phpMyAdmin SQL Dump
-- version 3.5.8.1
-- http://www.phpmyadmin.net
--
-- Host: 127.4.55.129:3306
-- Generation Time: Jun 13, 2013 at 05:04 PM
-- Server version: 5.1.69
-- PHP Version: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `trump`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'vilina penkova', 'c95f219ab0cea23e995b9e5360c2c6af3076eab1'),
(2, 'admin', '465f2315f26b8cbc6c168b1fc2d4ccec34362906');

-- --------------------------------------------------------

--
-- Table structure for table `fb_user`
--

CREATE TABLE IF NOT EXISTS `fb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `coins` int(11) DEFAULT NULL,
  `medals1` int(11) DEFAULT NULL,
  `medals2` int(11) DEFAULT NULL,
  `medals3` int(11) DEFAULT NULL,
  `medals4` int(11) DEFAULT NULL,
  `medals5` int(11) DEFAULT NULL,
  `medals6` int(11) DEFAULT NULL,
  `medals7` int(11) DEFAULT NULL,
  `medals8` int(11) DEFAULT NULL,
  `medals9` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `fb_user`
--

INSERT INTO `fb_user` (`id`, `uid`, `name`, `points`, `picture`, `coins`, `medals1`, `medals2`, `medals3`, `medals4`, `medals5`, `medals6`, `medals7`, `medals8`, `medals9`) VALUES
(1, 100005616783904, 'Ook GameQuiz', 196, 'https://graph.facebook.com/100005616783904/picture?type=large', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 100003370941445, 'Rosen Ecard', 195, 'https://graph.facebook.com/100003370941445/picture?type=large', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 100000610508000, 'Инна Михайлова', 110, 'https://graph.facebook.com/100000610508000/picture?type=large', 16, 0, 33, 0, 10, 0, 1, 61, 1, 0),
(5, 100005021552691, 'Вилина Пенкова', 254, 'https://graph.facebook.com/100005021552691/picture?type=large', 2, 10, 10, 10, 9, 9, 10, 12, 10, 11),
(6, 100001582030980, 'Rosen Tihomirov', 200, 'https://graph.facebook.com/100001582030980/picture?type=large', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(7, 100000378657770, 'Desislava Valerieva', 199, 'https://graph.facebook.com/100000378657770/picture?type=large', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(8, 100004188247100, 'Ivelina Dishkova', 200, 'https://graph.facebook.com/100004188247100/picture?type=large', 10, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(9, 574770474, 'Peter Sabev', 202, 'https://graph.facebook.com/574770474/picture?type=large', 10, 0, 0, 1, 0, 0, 0, 0, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE IF NOT EXISTS `game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `player1uid` bigint(20) DEFAULT NULL,
  `player2uid` bigint(20) DEFAULT NULL,
  `player3uid` bigint(20) DEFAULT NULL,
  `player4uid` bigint(20) DEFAULT NULL,
  `is_start` tinyint(1) DEFAULT '0',
  `is_finish` tinyint(1) DEFAULT '0',
  `start` datetime DEFAULT NULL,
  `finish` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`id`, `player1uid`, `player2uid`, `player3uid`, `player4uid`, `is_start`, `is_finish`, `start`, `finish`) VALUES
(3, NULL, 100005021552691, 100000610508000, NULL, 1, 1, '2013-04-02 13:26:11', '2013-04-02 13:27:37'),
(4, 100005021552691, NULL, NULL, NULL, 1, 1, '2013-04-07 13:24:25', '2013-04-07 13:24:31'),
(5, 100005021552691, NULL, NULL, NULL, 1, 1, '2013-04-09 13:59:06', '2013-04-09 13:59:14'),
(6, 100000610508000, NULL, NULL, NULL, 1, 1, '2013-05-16 05:47:05', '2013-05-16 05:50:50'),
(7, 100000610508000, NULL, NULL, NULL, 1, 1, '2013-06-10 15:10:08', '2013-06-10 15:12:02');

-- --------------------------------------------------------

--
-- Table structure for table `play_evolutions`
--

CREATE TABLE IF NOT EXISTS `play_evolutions` (
  `id` int(11) NOT NULL,
  `hash` varchar(255) CHARACTER SET latin1 NOT NULL,
  `applied_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `apply_script` text CHARACTER SET latin1,
  `revert_script` text CHARACTER SET latin1,
  `state` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `last_problem` text CHARACTER SET latin1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `play_evolutions`
--

INSERT INTO `play_evolutions` (`id`, `hash`, `applied_at`, `apply_script`, `revert_script`, `state`, `last_problem`) VALUES
(1, '64218c976760bac6e3a3b0549e13fa55d473d372', '2013-04-02 08:39:56', 'create table admin (\nid                        bigint auto_increment not null,\nusername                  varchar(255),\npassword                  varchar(255),\nconstraint pk_admin primary key (id))\n;\n\ncreate table fb_user (\nid                        bigint auto_increment not null,\nuid                       bigint,\nname                      varchar(255),\npoints                    integer,\npicture                   varchar(255),\ncoins                     integer,\nmedals1                   integer,\nmedals2                   integer,\nmedals3                   integer,\nmedals4                   integer,\nmedals5                   integer,\nmedals6                   integer,\nmedals7                   integer,\nmedals8                   integer,\nmedals9                   integer,\nconstraint pk_fb_user primary key (id))\n;\n\ncreate table game (\nid                        bigint auto_increment not null,\nplayer1uid                bigint,\nplayer2uid                bigint,\nplayer3uid                bigint,\nplayer4uid                bigint,\nis_start                  tinyint(1) default 0,\nis_finish                 tinyint(1) default 0,\nstart                     datetime,\nfinish                    datetime,\nconstraint pk_game primary key (id))\n;\n\ncreate table question (\nid                        bigint auto_increment not null,\nquestion                  varchar(255),\nright_answer              varchar(255),\ncategory                  integer,\nanswer1                   varchar(255),\nanswer2                   varchar(255),\nanswer3                   varchar(255),\nchoice_answer1            integer,\nchoice_answer2            integer,\nchoice_answer3            integer,\nchoice_answer4            integer,\nconstraint pk_question primary key (id))\n;\n\ncreate table user_question (\nid                        bigint auto_increment not null,\nquestion                  varchar(255),\nright_answer              varchar(255),\ncategory                  integer,\nanswer1                   varchar(255),\nanswer2                   varchar(255),\nanswer3                   varchar(255),\nusername                  varchar(255),\nuser_uid                  bigint,\nconstraint pk_user_question primary key (id))\n;', 'SET FOREIGN_KEY_CHECKS=0;\n\ndrop table admin;\n\ndrop table fb_user;\n\ndrop table game;\n\ndrop table question;\n\ndrop table user_question;\n\nSET FOREIGN_KEY_CHECKS=1;', 'applied', '');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `right_answer` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `answer1` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `answer3` varchar(255) DEFAULT NULL,
  `choice_answer1` int(11) DEFAULT NULL,
  `choice_answer2` int(11) DEFAULT NULL,
  `choice_answer3` int(11) DEFAULT NULL,
  `choice_answer4` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=175 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `question`, `right_answer`, `category`, `answer1`, `answer2`, `answer3`, `choice_answer1`, `choice_answer2`, `choice_answer3`, `choice_answer4`) VALUES
(29, 'Кой е основал българската държава?', 'Хан Аспарух', 1, 'Хан Крум', 'Хан Тервел', 'Хан Кубрат', 0, 0, 0, 0),
(30, 'Кога е създадена българската държава?', '681', 1, '1681', '864', '618', 0, 0, 0, 0),
(31, 'Кой е първият американски президент?', ' Джордж Вашингтон', 1, ' Томас Джеферсън ', 'Роналд Уилсън Рейган', 'Теодор Рузвелт', 0, 0, 0, 0),
(32, 'Какъв е Севт III?', 'тракийски владетел', 1, ' египетски фараон ', 'персийски шах', 'картагенски водач', 0, 0, 0, 0),
(33, 'Кой хан управлява България през 807 година?', 'Хан Крум', 1, 'Хан Тервел', 'Хан Омуртаг', 'Цар Борис', 0, 0, 0, 0),
(34, 'През коя година е открита Америка?', '1492', 1, '1787', '1168', '854', 0, 0, 0, 0),
(35, 'Кой според легендата е произнесъл думите "И все пак тя се върти!"?', 'Галилео Галилей', 1, 'Никола Тесла', 'Леонардо да Винчи', 'Николай Коперник', 0, 0, 0, 0),
(36, 'Колко години е продължила 100 годишната война?', '116 години', 1, '100 години', '99 години', '101 години', 0, 0, 0, 0),
(37, 'В коя държава е била битката Ватерло?', 'Белгия', 1, 'Чехия', 'Полша', 'Великобритания', 0, 0, 0, 0),
(38, 'Какво е открил Христофор Колумб?', 'Америка', 1, 'Китай', 'Луната', 'Индия', 0, 0, 0, 0),
(39, 'Какво е открил Вилхелм Рьонтген през 1895?', ' Рентгеновите лъчи', 1, 'Рентгенов апарат', 'Ядрения полуразпад', 'Честотата на вълната', 0, 0, 0, 0),
(40, 'Какъв е по народност Сталин?', 'Грузинец', 1, 'Руснак', 'Индиец', 'Българин', 0, 0, 0, 0),
(41, 'Какво използва Архимед, за да подпали римските кораби обсадили Сиракуза?', 'Огледала', 1, 'Макари', 'Лава', 'Огнени стрели', 0, 0, 0, 0),
(42, 'В кой град е убит Джон Кенеди?', 'Далас', 1, 'Хюстън', 'Джорджтаун', 'Елдорадо', 0, 0, 0, 0),
(43, 'През коя година Хан Омуртаг се възкачва на престола?', '814', 1, '841', '956', '712', 0, 0, 0, 0),
(44, 'В коя страна се намира Ватиканът?', 'Ватикана', 6, 'Испания', 'Италия', 'Германия', 0, 0, 0, 0),
(47, 'Коя е столицата на Австрия?', 'Виена', 6, 'Бон', 'Берлин', 'Венеция', 0, 0, 0, 0),
(48, 'Атина е столица на?', 'Гърция', 6, 'Кипър', 'Сицилия', 'Малта', 0, 0, 0, 0),
(50, 'Коя е столицата на Австралия?', 'Канбера', 6, 'Сидни', 'Мелбърн', 'Пърт', 0, 0, 0, 0),
(51, 'Коя е столицата на Албания', 'Тирана', 6, 'Скопие', 'Прищина', 'Истанбул', 0, 0, 0, 0),
(52, 'Коя е столицата на Алжир?', 'Алжир', 6, 'Оран', 'Буира', 'Шлеф', 0, 0, 0, 0),
(53, 'Коя е столицата на Армения?', 'Ереван', 6, 'Минск', 'Кабул', 'Бейрут', 0, 0, 0, 0),
(54, 'Най-големият остров на Земята,Гренландия е територия на:', 'Дания', 6, 'Канада', 'САЩ', 'Русия', 0, 0, 0, 0),
(55, 'Коя е най-голямата по площ държава в Африка?', 'Судан', 6, 'Алжир', 'Либия', 'ДР Конго', 0, 0, 0, 0),
(56, 'Коя азиатска държава е известна като „страната на изгряващото слънце”', 'Япония', 6, 'Китай', 'Филипини', 'Корея', 0, 0, 0, 0),
(57, 'Кое е най-високото плато в Азия:', 'Тибетско', 6, 'Декан', 'Анадолско', 'Средносибирско', 0, 0, 0, 0),
(58, 'С кои държави граничи България на юг?', 'Гърция и Турция', 6, 'Сърбия и Македония', 'Румъния', 'Други', 0, 0, 0, 0),
(59, 'Житницата на България е:', 'Добруджа', 6, 'Софийското поле', 'Казанлъжката котловина', 'Друга', 0, 0, 0, 0),
(60, 'Колко е площта на пустинята Сахара ?', '9 млн. кв. км.', 6, '6 млн. кв. км.', '7 млн. кв. км. ', ' 8 млн. кв. км.', 0, 0, 0, 0),
(61, 'С кой океан граничи на запад континента Европа ?', 'Атлантически океан', 6, 'Тихи океан', 'Северния ледовит океан', 'Друг', 0, 0, 0, 0),
(62, 'Кой е първият човек, стъпил на Луната?', 'Нийл Армстронг', 7, 'Дейвид Скот', 'Чарлс Конрад', 'Ричарт Гордън', 0, 0, 0, 0),
(63, 'Как се нарича най-близкото до земята небесно тяло?', 'Луна', 7, 'Андромеда ', 'Марс', 'Венера', 0, 0, 0, 0),
(64, 'Коя е най-близката планета до слънцето?', 'Меркурий', 7, 'Нептун', 'Уран', 'Марс', 0, 0, 0, 0),
(65, 'Коя планета от слънчевата система има пръстени?', 'Сатурн', 7, 'Марс  ', 'Венера', 'Плутон', 0, 0, 0, 1),
(66, 'За колко дни Луната обикаля около Земята?', '27', 7, '31', '16', '28', 0, 0, 0, 0),
(67, 'От коя планина извира река Ропотамо?', 'Странджа', 6, ' Драконовите планини', 'Пирин Апенини', 'Того', 0, 0, 0, 0),
(68, 'През коя година Нийл Армстронг стъпва на луната?', '1969 година', 7, '1944 година', '1982 година', '1991 година', 0, 0, 0, 0),
(69, 'Колко на брой са планетите в Слънчевата система?(само цифра)', '8', 7, '10', '9', '11', 0, 0, 0, 0),
(70, 'Как е името на най-близката до нас звезда?', 'Слънце', 7, 'Андромеда', 'Бетелгейзе', 'Полярната звезда', 0, 0, 0, 0),
(71, 'За колко години Халеевата комета обикаля слънцето?', '76 години', 7, '77', '110 години', '5000 години', 0, 0, 0, 0),
(72, 'Коя е най-отдалечената планета от слънцето ?', 'Нептун', 7, 'Уран', 'Меркурий', 'Плутон', 0, 0, 0, 0),
(73, 'Как се нарича телескопът, който ще наследи Хъбъл?', 'Джеймс Уеб', 7, 'Чандра Кеплер', 'Телескоп на Ричи-Кретиен', 'Телескоп на Максутов', 0, 0, 0, 0),
(74, 'По кое време на годината се наблюдават бели нощи?', 'лятото', 7, 'пролетта', 'зимата', 'есента', 0, 0, 0, 0),
(75, 'На колко проблизително е равно числото Пи?', '3,14', 2, '116', '6,12', '10', 0, 0, 0, 0),
(76, 'Кое е следващото число в редицата: 1, 1, 2, 3, 5, 8, 13...?', '21', 2, '20', '19', '17', 0, 0, 0, 0),
(77, 'В кой от следните филми не участва Джони Деп?', 'Адвокат на дявола', 9, 'Чарли и шоколадовата фабрика', 'От Ада', 'Карибски пирати', 0, 0, 0, 0),
(78, 'Кой футболист е играл и за двата ни гранда "Левски" и "ЦСКА"?', 'Станимир Стоилов', 5, 'Ивайло Йорданов', ' Георги Иванов - Гонзо', 'Любослав Пенев', 0, 0, 0, 0),
(79, 'На колко кубични сантиметра се равнява 1 литър?', '1000', 2, '10', '100', '1', 0, 0, 0, 0),
(81, 'Колко е 5! (факториел)?', '120', 2, '125', '200', '110', 0, 0, 0, 0),
(85, 'Кой от изброените методи е приложим в математиката?', 'Метод на Гаус - Жордан ', 2, 'Метод на Мария Монтесори', 'Метод на Пипън - Джордан', 'Метод на медицината', 0, 0, 0, 0),
(86, 'Колко клапи има в нормалното човешко сърце?', '4', 2, '10', '2', '3', 0, 0, 0, 0),
(87, 'Как се наричат химичните елементи, изграждащи живите организми?', 'Биогенни', 2, 'Газове', 'Метали', 'Радиоактивни', 0, 0, 0, 0),
(88, 'Какво означава абревиатурата W W W?', 'world wide web ', 8, 'world wild web', 'windows with web', 'world wild wait', 0, 0, 0, 0),
(89, 'Кой художник е нарисувал "Слънчогледите"?', 'Винсент ван Гог ', 3, 'Пабло Пикасо', 'Клод Моне', 'Леонардо да Винчи', 0, 0, 0, 0),
(90, 'Кой актьор изпълнява ролята на Индиана Джоунс в едноименната поредица?', ' Харисън Форд', 9, 'Джеймс Каан', 'Марлон Брандо', 'Ед Харис', 0, 0, 0, 0),
(91, 'Защо небето е синьо?', 'заради късовълновите лъчи от слънчевия спектър', 2, 'заради дълговълновите лъчи от слънчевия спектър', 'илюзия създадена от облаците', ' илюзия създадена от въздушните течения', 0, 0, 0, 0),
(92, 'Къде е роден Владимир Димитров - Майстора?', 'с. Фролош', 3, 'гр. Кюстендил', 'гр. Перник', 'с. Жиленци', 0, 0, 0, 0),
(93, 'С коя програма се създават електронни таблици?', 'Microsoft Excel  ', 8, 'Microsoft PowerPoint  ', 'Microsoft Publisher', 'Windows Movie Maker  ', 0, 0, 0, 0),
(94, 'Кой е режисьорът на филма "Алиса в страната на чудесата"(2010)?', 'Тим Бъртън', 9, 'Франсис Форт Копола', 'Стивън Содърбърг', 'Стивън Спилбърг', 0, 0, 1, 0),
(95, 'Колко Оскара получава филмът "Смело сърце" (1995)?', '5', 9, '3', '6', '4', 0, 1, 0, 0),
(96, 'Режисьор на филма "Джурасик Парк" е?', 'Стивън Спилбърг', 9, 'Мартин Скорсезе', 'Клинт Истуд', 'Люк Бесон', 0, 0, 0, 1),
(97, 'Кой е авторът на книгата "Наследникът на лилиите"?', 'Рафаел Сабатини', 4, 'Жул Верн', 'Александър Дюма', 'Емилио Салгари', 0, 0, 0, 0),
(98, 'На кой литературен герой принадлежат думите: "И едните и другите са маскари! Ти мене слушай па се не бой! Маскари са до един!"?', 'Бай Ганьо ', 4, 'дядо Матейко', 'чорбаджи Марко', 'Андрешко', 0, 0, 0, 0),
(99, 'На колко е равно 2+2x2?', '6', 2, '8', '10', '12', 0, 0, 0, 0),
(100, 'Кои числа от 0 до 10 са прости?', '2, 3, 5, 7', 2, '1, 2, 3, 5, 7', '1, 2, 3, 5, 7, 9', '2, 3, 7, 9', 0, 0, 0, 0),
(101, 'Колко на брой са устойчивите изотопи на кислорода?', '3', 2, '5', '2', '11', 0, 0, 0, 0),
(102, 'Кой химик изобретява рудничната лампа?', 'Хъмфри Дейви', 2, 'Майкъл Фарадей', 'Уилям Хенри', 'Джон Далтон', 0, 0, 0, 0),
(103, 'Кое твърдение НЕ се отнася за въглеродния диоксид?', 'молекулата му се състои от 2 атома въглерод и 1 атом кислород', 2, 'той е газ без цвят и миризма', 'участва като изходно вещество при фотосинтезата на растенията', 'ако се натрупа в по-големи количества в атмосферата, това води до парников ефект', 0, 0, 0, 0),
(104, 'През коя година Лувърът отваря врати за посетители? ', '1793', 3, '1801', '1836', '1839', 1, 0, 0, 0),
(105, 'Кой е първият носител на "Златната топка"?', 'Стенли Матюс ', 5, 'Раймон Копа', 'Алфредо ди Стефано', 'Еузебио', 0, 0, 0, 0),
(106, 'Кой стана световен шампион по снукър през 2009 година?', 'Джон Хигинс', 5, 'Рони О"Съливан', 'Нийл Робъртсън', 'Шон Мърфи', 0, 0, 0, 0),
(107, 'Как се нарича уредът, чрез който се измерва електричния ток?', 'Амперметър', 2, 'Омомер', 'Резистор', 'Електромер', 0, 0, 0, 0),
(108, 'Кой е изобретил асансьора?', 'Иван Кулибин', 8, 'Иван Алексиев', 'Алексей Иванович', 'Петър Василев', 0, 0, 0, 0),
(109, 'Кой от изброените сериали е български?', '"Забранена любов"', 9, '"Дъждовно време"', '"Отчаяни съпруги"', '"Частна практика"', 0, 0, 0, 0),
(110, 'На кого принадлежат думите "Сюрреализмът, това съм аз!"?', 'Салвадор Дали', 3, 'Луи Арагон', 'Андре Бретон', 'Хуан Миро', 0, 0, 0, 1),
(111, 'Откъде идва името на компютъра "Сетун"?\r\n', 'От името на река', 8, 'От инициалите на създателите му', 'От английската дума set', 'От името на град', 0, 0, 0, 0),
(112, 'Коя от операционните системи НЕ е GNU/Linux дистрибуция?', ' Kolibri', 8, 'Arch', 'Sabayon', 'TinyMe', 0, 0, 0, 0),
(113, 'Коя двойка от изброените растения принадлежи към едно семейство?', 'Есхинантус и Стрептокарпус', 2, 'Непентис и Нертера', 'Колерия и Ктенанте', 'Хирита и Хоризема', 0, 0, 0, 0),
(114, 'Какво е каудекс?', 'късо, дебело стъбло на растение', 2, 'видоизменена част от листата на растение', 'вид бобово растение', 'растителна клетка', 0, 0, 0, 0),
(115, 'Кое от изброените липсва в устройството на растителните клетки?', 'Глококаликс ', 2, 'Вакуола', 'Рибозоми', 'Цитоскелет', 0, 0, 0, 0),
(116, 'Орхидеята е от тип:', 'покритосеменни растения', 2, ' голосеменни растения', 'папратовидни растения', 'псилотовидни растения', 0, 0, 0, 0),
(117, 'Кое от изброените животни НЕ притежава бял дроб?', 'Акула', 2, 'Кит', 'Тюлен', 'Видра', 0, 0, 0, 0),
(118, 'Коя от изброените гъби е отровна?', 'Тигрова гъба ', 2, 'Саждива гъба', 'Сярна гъба', 'Червеновърха коралка', 0, 0, 0, 0),
(119, 'На колко сегмента е разделен гръбначният мозък?', '31', 2, '25', '14', '20', 0, 0, 0, 0),
(120, 'Кой е авторът на картината "Дамата с хермелина"?', 'Леонардо да Винчи', 3, 'Сандро Ботичели', 'Рафаело', 'Микеланджело', 0, 0, 0, 0),
(121, 'При кой живописец за първи път е изпратен да се учи Леонардо да Винчи?', 'Андреа дел Верокио', 3, 'Джорджо Вазари', 'Джото', 'Фра Анджелико', 0, 0, 0, 0),
(122, 'Коя от изброените форми на традиционния японски театър е и най-старата в света?', 'ногаку', 3, 'бунраку', 'кьоген', 'кабуки', 0, 0, 0, 0),
(123, 'Какво изобразява "Герника" на Пикасо?', 'Бомбардировка', 3, 'Портрет', 'Жътварки', 'Геометрични фигури', 1, 0, 1, 0),
(124, 'Какъв освен велик художник е бил Пабло Пикасо?', 'Скулптор ', 3, 'Дърводелец', 'Миньор', 'Учен', 0, 0, 0, 0),
(125, 'Къде се съхранява оригиналът на картината "Мона Лиза"?', 'Лувъра в Париж', 3, 'Музея Райксмузеум в Амстердам', 'Музея Бойманс ван Бойнинген в Ротердам', ' Музея на модерното изкуство в Ню Йорк', 0, 0, 0, 0),
(126, 'Чии фрески от 1964 г. красят тавана на Парижката опера?', 'Марк Шагал', 3, 'Анри Матис', 'Жорж Брак', 'Василий Кандински', 1, 1, 0, 0),
(127, 'Колко различни позиции на тялото съчетава в себе си Леонардовият "Витрувиански човек"?', '16', 3, '10', '4', '9', 0, 0, 0, 0),
(128, 'В кой музей е изложена картината на Рафаело "Сикстинската мадона"?', 'Цвингера', 3, 'Лувъра', 'Пратера', 'Сикстинската капела', 0, 0, 0, 0),
(129, 'Кой от импресионистите е аристократ по рождение?', 'Едгар Дега', 3, 'Пол Сезан', 'Клод Моне', 'Винсент Ван Гог', 0, 0, 0, 0),
(130, 'На кое течение в изкуството е представител Ван Гог?', 'постимпресионизъм', 3, 'експресионизъм', 'символизъм', 'модернизъм', 0, 0, 0, 0),
(131, 'Коя е най-известната картина на известния норвежки художник Едвард Мунк?', '"Викът"', 3, '"Пепел"', '"Картоиграчи"', '"Водни лилии"', 0, 0, 0, 0),
(132, 'Колко автопортрета е нарисувал Винсент ван Гог?', '35', 3, '0', '12', '40', 0, 0, 0, 0),
(133, 'В коя книга на Агата Кристи всички заподозрени участват в убийството?', '"Убийство в Ориент Експрес" ', 4, '"Азбучните убийства"', '"10 малки негърчета"', '"Убийство край Нил"', 0, 0, 0, 0),
(134, 'Коя от посочените творби не е от Исабел Алиенде?', '"Сто години самота"', 4, '"Къщата на духовете"', '"Паула"', ' "Портрет в сепия"', 0, 0, 0, 0),
(135, 'Как се казва кръстникът на Хари Потър от едноименната поредица на Дж. К. Роулинг?', 'Сириус', 4, 'Джеймс', ' Върнън', 'Лупин', 0, 0, 0, 0),
(136, 'Коя култова книга е била отхвърлена от 121 издателства, преди да бъде издадена, което е и рекорд на Гинес?', '"Зен и изкуството да се поддържа мотоциклет" - Робърт Пърсиг', 4, '"Полет над кукувиче гнездо" - Кен Киси', ' "По пътя" - Джак Керуак', '"Пътуване към Икстлан" - Карлос Кастанеда', 0, 0, 0, 0),
(137, 'Изразът: "Балканът пее хайдушка песен!" е: ', 'лицетворение', 4, 'символ', ' парадокс', 'синекдоха', 0, 0, 0, 0),
(138, 'В кой от редовете ВЯРНО са свързани автор и литературна творба?', 'Алеко Константинов - "До Чикаго и назад"', 4, 'Пейо Яворов - "Неразделни"', ' Христо Ботев - "Немили-недраги"', 'Елин Пелин - "Една българка"', 0, 0, 0, 0),
(139, 'Кой известен роман започва с изречението "Наричай ме Исмаил"?', '"Моби Дик" ', 4, '"Край река Пиедра седнах и заплаках"', '"Човекът, който се смее"', ' "Спасителят в ръжта"', 0, 0, 0, 0),
(140, 'През коя година е роден asтронавтът Нийл Армстронг?', '1930', 7, '1964', '1896', '1945', 0, 0, 0, 0),
(141, 'Коя планета от Слънчевата система не е скалиста ?', 'Нептун', 7, 'Плутон', 'Сатурн', 'Земя', 0, 0, 0, 0),
(142, 'Кой е първия български интернет каталог чието име съвпада с името на традиционно българско ястие? ', 'Гювеч', 8, 'Тутманик', 'Баница', 'Каварма', 0, 0, 0, 0),
(143, 'Кой изобретява първите пневматични гуми?', 'Дънлоп', 8, 'Мишелин', 'Гуд Ийр', 'Кохинор', 0, 0, 0, 0),
(144, 'Как се изписва съкратено Hypertext Pre-Processor?', 'PHP', 8, 'PHH', 'HTML', 'HPR', 0, 0, 0, 0),
(145, 'Как се нарича аналоговият сигнал който е дискретизиран във времето, и квантуван по ниво?', 'Цифров сигнал', 8, 'Хармоничен сигнал', 'Дискретен сигнал', 'Квантован сигнал', 0, 0, 0, 0),
(146, 'През коя година е изобретен телефонът?', '1875', 8, '1954', '1831', '1789', 0, 0, 0, 0),
(147, 'Как се нарича технологията за приемане и предаване на движещ се образ и звук на разстояние?', 'Телевизия', 8, 'Телеграф', 'Телефон', 'Радио', 0, 0, 0, 1),
(148, 'Как се нарича устройството, в което от пълния цветен телевизионен сигнал се получават основните цветни сигнали?', 'Декодер', 8, 'Рекордер ', 'Цифров сигнализатор', 'Цифров администратор', 0, 0, 0, 0),
(149, 'Как се казва актьорът, който играе в "Кръстникът” и "Адвокат на дявола"?', 'Ал Пачино', 9, 'Брат Пит', 'Робърт Дениро ', 'Антонио Бандерас', 0, 0, 0, 1),
(150, 'Как се казва катерицата от "Ледена епоха"?', 'Скрат', 9, 'Сид', 'Диего', 'Ели', 0, 0, 0, 1),
(151, 'Коя птица научава Др. Дулитъл да говори с животните?', 'Папагала', 9, 'Хипопотама', 'Котката', 'Бухала', 0, 0, 0, 0),
(152, 'Кой актьор играе главната роля (на Ваньо) във филма "Рицар без броня" ?', 'Олег Ковачев', 9, 'Вихър Стойчев', 'Цвятко Николов', 'Апостол Карамитев', 0, 0, 0, 0),
(153, 'В кой спорт Петя Неделчева е печелила 6 пъти Европейската купа?', 'Бадминтон', 5, 'Дълъг скок', 'Волейбол ', 'Плуване', 0, 0, 0, 0),
(154, 'От колко пръстена се състои олимпийския символ?', '5', 5, '8', '10', '6', 0, 0, 0, 0),
(155, 'Колко отбора участват на Първото Световно първенство по футбол през 1930 г.?', '13', 5, '18', '12', '10', 0, 0, 0, 0),
(156, 'В коя държава през 1930 г. се провежда първото Световно първенство по футбол?', 'Уругвай', 5, 'Дания', 'Холандия', 'Германия', 0, 0, 0, 0),
(157, 'Какъв е прякорът на бившият български футболист и треньор Христо Стойчков?', 'Камата', 5, 'Топката', 'Кобрата', 'Главата', 0, 0, 0, 0),
(158, 'Кога е роден известният футболист Георги Аспарухов-Гунди?', '1943', 5, '1942', '1964', '1938', 0, 0, 0, 0),
(159, 'Кое е основното платно при ветроходната яхта?', 'Грот', 5, 'Спинакер', 'Генуа', 'Кърма', 0, 0, 2, 0),
(160, 'Кой е написал романа "Тютюн"?Александър Томов', 'Димитър Димов', 4, ' Христо Калчев', 'Александър Томов', 'Димитър Талев', 0, 0, 0, 0),
(161, 'Как се казва първото стихотворение на Христо Ботев?', 'Майце си', 4, 'Българския Бог', 'Шипка', 'На прощаване', 0, 0, 0, 0),
(162, 'Кой е авторът на романа "Морският вълк"?', 'Джек Лондон', 4, ' Джон Стайнбек', 'Хелън Филдинг', 'Дан Браун', 0, 0, 0, 0),
(163, 'Кой е автора на "Мълчанието на агнетата"?', 'Томас Харис', 4, 'Гюстав Флобер ', 'Хелън Филдинг', 'Салман Рушди', 0, 0, 0, 0),
(164, '"Братя Карамазови" е роман на...?', 'Достоевски', 4, 'Радослав Парушев Оноре дьо Балзак', 'Виктор Юго', 'Оноре дьо Балзак', 0, 0, 0, 0),
(165, 'През коя година е публикуван романът " Махалото на Фуко" ?', '1988', 4, ' 2001', '1898', '1990', 0, 0, 0, 0),
(166, 'Как умира Пейо Яворов?', 'Самоубива се', 4, 'Одавя се', 'Старост ', 'Разстрелян', 0, 0, 0, 0),
(167, 'Кого наричат "бащата на трагедията"?', 'Есхил', 4, 'Калдерон', 'Софокъл', 'Аристофан', 0, 0, 0, 0),
(168, 'Как се казва книгата, която Кари Брадшоу чете на Тузаря в "Сексът и градът"?', 'Запечатано с целувка', 9, ' Хамелеон ', 'Любов по неволя', 'Скандална нощ', 0, 0, 0, 0),
(169, 'Коя е авторката на бестселъра "Сексът и градът", залегнал в основата на хитовия сериал на НВО?', 'Кандис Бушнел', 4, 'Гуин Дайър', 'Елизабет Бърг', 'Андрю Блейк', 0, 0, 0, 0),
(170, 'Кой е най-големия стадион в България?', 'Васил Левски', 5, ' Георги Аспарухов', 'Българска армия', 'Локомотив', 0, 0, 0, 0),
(171, 'Колко сантиметра е висока мрежата за тенис на маса?', '15', 5, '16', '20', '25', 0, 0, 0, 0),
(172, 'Коя шахматна фигура се движи по буквата Г ?', 'Кон', 5, 'Цар', 'Пешка', 'Офицер', 0, 0, 0, 0),
(173, 'През коя година е починал българският футболист Гунди (Георги Аспарухов Рангелов)?', '1971', 5, '1998', '1899 ', '1989', 0, 0, 0, 0),
(174, 'През коя година е роден е роден футболиста Зинедин Зидан?', '1972', 5, '1974', '1963', '1981', 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_question`
--

CREATE TABLE IF NOT EXISTS `user_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `right_answer` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `answer1` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `answer3` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_uid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
