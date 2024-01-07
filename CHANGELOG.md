## [1.6.1](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.6.0...v1.6.1) (2024-01-07)


### Bug Fixes

* change fields to accept Timestamps ([9545165](https://github.com/AirshipCraft/AC-CommonLib/commit/9545165486c35835ee8916f7d6ac12d3b563fb62))
* flesh out methods + add comments ([bab3f55](https://github.com/AirshipCraft/AC-CommonLib/commit/bab3f553c34daba477fc4af87ffd5b38b6f893d7))

# [1.6.0](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.5.0...v1.6.0) (2024-01-07)


### Bug Fixes

* add equal and hashcode methods ([7550561](https://github.com/AirshipCraft/AC-CommonLib/commit/7550561f44cff8c0756e0c389f117180ffb3c80c))
* fix implementation of GenericDao ([d002655](https://github.com/AirshipCraft/AC-CommonLib/commit/d00265552b1af62d25a776dbed86fa802f02a2af))
* fix small issue with configuring this field ([de0737d](https://github.com/AirshipCraft/AC-CommonLib/commit/de0737de536139729753ecdfdc378c82a9c63259))
* fix using commonlib instead of acrplugin ([2e5f19b](https://github.com/AirshipCraft/AC-CommonLib/commit/2e5f19b8d0e9948019de057932519b2f756501bb))
* init eventmanager + add comments ([160bb6b](https://github.com/AirshipCraft/AC-CommonLib/commit/160bb6baf57d57cf406910821a9e1b26a8afe390))
* make fields optional in AbstractGameEvent ([0d5fed4](https://github.com/AirshipCraft/AC-CommonLib/commit/0d5fed47a45d9563da2b4d86ddb7e6463ac7d1e8))
* move over to using CustomDate ([361ce68](https://github.com/AirshipCraft/AC-CommonLib/commit/361ce687416f94de4008f76f4074fdeabed1888d))
* print stack trace ([2c7df40](https://github.com/AirshipCraft/AC-CommonLib/commit/2c7df40a073de6470cd933325a773ffa8c848cf5))
* remove redundant method call ([099df68](https://github.com/AirshipCraft/AC-CommonLib/commit/099df688a1d1fced09d62da40f3354a8c98e89b4))
* remove redundant methods ([f473221](https://github.com/AirshipCraft/AC-CommonLib/commit/f473221317ad7646432a7bfc2727a5a337ce7845))
* remove references to LocalDate, purely using CustomDate now ([9b1991f](https://github.com/AirshipCraft/AC-CommonLib/commit/9b1991fcb3e904514cd920cdc2df031f555595a1))
* small fix in using correct gameevent ([4c1b6c1](https://github.com/AirshipCraft/AC-CommonLib/commit/4c1b6c17736e2e844b740c2c1e8d047ccc230783))
* switch to CustomDate (missed fields) ([166c2ee](https://github.com/AirshipCraft/AC-CommonLib/commit/166c2eee9e31f2d73738c1fee1bac5a6760f9500))
* switch to using custom date ([3ebac86](https://github.com/AirshipCraft/AC-CommonLib/commit/3ebac8677642d17f28f9f4324423cd3869660c19))
* switch to using custom date ([56f75bf](https://github.com/AirshipCraft/AC-CommonLib/commit/56f75bfdd1a3b9870250e8a7c0c42b7d0dff4f0c))
* update CRUD methods to reflect the interface ([8a28f61](https://github.com/AirshipCraft/AC-CommonLib/commit/8a28f61fa7ac9e8e87a454a94ef63a1b69986017))
* update init of cal manager ([7f4d117](https://github.com/AirshipCraft/AC-CommonLib/commit/7f4d11789463fc4b5a8a4af4464fb10197409e3e))
* update saving and loading methods ([f4c6bb7](https://github.com/AirshipCraft/AC-CommonLib/commit/f4c6bb7a6b0d27c0e82eb1b2f0ff4f41f0e8caf7))


### Features

* abstraction of game event into its own classes ([7d4a829](https://github.com/AirshipCraft/AC-CommonLib/commit/7d4a829453cab7c6dc24c861a928c588e0705d45))
* add additional transaction methods ([302b544](https://github.com/AirshipCraft/AC-CommonLib/commit/302b54436aaf48025555b8684447dbd9fc6e7a50))
* add authdata model ([bf0e79a](https://github.com/AirshipCraft/AC-CommonLib/commit/bf0e79a4c5d0eb0590e33bed7bec2d418e71c381))
* add bunch of methods for getting values ([f66d70a](https://github.com/AirshipCraft/AC-CommonLib/commit/f66d70af8a74c735f338c29b81eee62becc87373))
* add equal and hashcode methods ([843530f](https://github.com/AirshipCraft/AC-CommonLib/commit/843530f4edb348816830484ba2e5a0bb4982110d))
* add findByUsername method ([6e3848d](https://github.com/AirshipCraft/AC-CommonLib/commit/6e3848da680b1328458e10a11535fe355f0fc5e8))
* add getters for worldclock and cal manager ([807fc5d](https://github.com/AirshipCraft/AC-CommonLib/commit/807fc5dd64a1f735f189d94b646d59cd8f086eef))
* add guide for using configuration classes ([1e975b2](https://github.com/AirshipCraft/AC-CommonLib/commit/1e975b2d847c4c84c3d44d20f29bc649af2a527a))
* add method for converting customdate to local ([0cd6d9e](https://github.com/AirshipCraft/AC-CommonLib/commit/0cd6d9edc1ccaa52578d877e7c5519a16c5d0440))
* add method for getting days until ([d0451ff](https://github.com/AirshipCraft/AC-CommonLib/commit/d0451ff9f93e6014945fdc318e7cd7ae5d5b7f05))
* add method for getting max poolsize ([ec56fa3](https://github.com/AirshipCraft/AC-CommonLib/commit/ec56fa3e925f0c3236297c3e714b7237fa049821))
* add methods for saving config and loading it ([4ea75fc](https://github.com/AirshipCraft/AC-CommonLib/commit/4ea75fcc9ed82dfb76a9ec8c37c42fec5be5f687))
* add more redis related methods ([7a124d0](https://github.com/AirshipCraft/AC-CommonLib/commit/7a124d085061c4757f3fe4c7c7effa12a2a2a847))
* add savestate and loadstate logic ([388a070](https://github.com/AirshipCraft/AC-CommonLib/commit/388a070852bf59e39940506fa51a162322f4e0ad))
* add snake YAML dependency ([0dc1d59](https://github.com/AirshipCraft/AC-CommonLib/commit/0dc1d591fefc7b4b21131b6455775b83ba7cec5d))
* add SQLAuthData DAO layer ([f8685eb](https://github.com/AirshipCraft/AC-CommonLib/commit/f8685eb969d8ec9473dc35bd2f91c1f7108a7f79))
* add table configuration interface ([1ea8e1f](https://github.com/AirshipCraft/AC-CommonLib/commit/1ea8e1f2da9bac4049142da5bb04d79df277a3bc))
* authdata DAO classes ([a598844](https://github.com/AirshipCraft/AC-CommonLib/commit/a598844232eb9b0af92d139bcdd6475f6a77367b))
* begun work on clock logic ([fd43f76](https://github.com/AirshipCraft/AC-CommonLib/commit/fd43f76cfd0e27e4eaf437c4a0b1a470e3b0e3d8))
* create abstract game event ([f15d582](https://github.com/AirshipCraft/AC-CommonLib/commit/f15d58209d70aa4692f9455f0f1bb8c3ba1c9658))
* create auth data dao ([aa19aa9](https://github.com/AirshipCraft/AC-CommonLib/commit/aa19aa930a27fa25c730530036a43543a4672b1a))
* create custom date class ([57659a9](https://github.com/AirshipCraft/AC-CommonLib/commit/57659a90761a5fa11521113a55758a9681c42ce5))
* create game event interface ([f3082ca](https://github.com/AirshipCraft/AC-CommonLib/commit/f3082ca77f60d1299ca0d5d6b09068bfc0955589))
* create implementation class for warning DAO ([03888bd](https://github.com/AirshipCraft/AC-CommonLib/commit/03888bd83cf15ea6cce2fc96c1f11fb523cd4621))
* create table manager class ([8e12ede](https://github.com/AirshipCraft/AC-CommonLib/commit/8e12ede6a3921ef32779c3c139c382205dfdd226))
* create warning object ([1ea325c](https://github.com/AirshipCraft/AC-CommonLib/commit/1ea325c2a6fbb4f1f2b84d6032506331958550b8))
* create WarningDao interface ([5e1ead3](https://github.com/AirshipCraft/AC-CommonLib/commit/5e1ead363e78370cde08f91ee254cb684cfbb21d))
* flesh out auth DAO ([5dbdd56](https://github.com/AirshipCraft/AC-CommonLib/commit/5dbdd5609a827e9808f298ac08ef2827ffa632b0))
* flesh out authdata model ([c913c56](https://github.com/AirshipCraft/AC-CommonLib/commit/c913c56687a8ae7a3e510b724abcfea6eff22b41))
* init worldclock ([52f2996](https://github.com/AirshipCraft/AC-CommonLib/commit/52f299657ac64002bb6af7b5e2b57c23006f4b70))
* integrate eventmanager ([3f4ddee](https://github.com/AirshipCraft/AC-CommonLib/commit/3f4ddeebc03366b1ec9fda0e55175ce8abaf5d19))
* switch to using customdate ([d01a562](https://github.com/AirshipCraft/AC-CommonLib/commit/d01a56233f4075f91c2c98279755093933eaaa71))
* update clock logic to sync w calendar manager ([abf1270](https://github.com/AirshipCraft/AC-CommonLib/commit/abf127079ca3a909cfb27a373bed544f98e9b872))
* update docs + add equal and hashcode methods ([3406280](https://github.com/AirshipCraft/AC-CommonLib/commit/3406280045f5b6d877723c644c51a95141edf107))
* update user model to better reflect DAO ([6793cc9](https://github.com/AirshipCraft/AC-CommonLib/commit/6793cc9d4513a144fc8604e71bc6d87d37c1d2c5))

# [1.5.0](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.12...v1.5.0) (2023-12-28)

### Bug Fixes

* GameEvent
  subclass ([9adc6a8](https://github.com/AirshipCraft/AC-CommonLib/commit/9adc6a88bb4be60a2219e55bbbe05cc441803d76))
* remove redundant season
  enum ([bd8e34e](https://github.com/AirshipCraft/AC-CommonLib/commit/bd8e34e6b3dfa018a4e67ed80e0565c76e550e36))
* update SQL connection manager
  class ([805878b](https://github.com/AirshipCraft/AC-CommonLib/commit/805878bf114956a7595f95c3719a49c6272923ee))

### Features

* add newDay
  method ([04910ee](https://github.com/AirshipCraft/AC-CommonLib/commit/04910ee71c5f1d7828ccb28149307dc1bfca194a))
* create calendar
  interface ([44de22a](https://github.com/AirshipCraft/AC-CommonLib/commit/44de22a24f4e0ed6fe3b36e16b9af60979181ec5))
* create calendar
  package ([099bf92](https://github.com/AirshipCraft/AC-CommonLib/commit/099bf92ce26df95fde90d765e3338a70e795e887))
* create calendarmanager
  class ([07cc6d3](https://github.com/AirshipCraft/AC-CommonLib/commit/07cc6d3b6d5fd77267f6130e83c6027ded9bb54a))
* create database config
  class ([526c8b5](https://github.com/AirshipCraft/AC-CommonLib/commit/526c8b5b67719b6ec44073e6553e1911ea2802fe))
* create event
  manager ([56261fc](https://github.com/AirshipCraft/AC-CommonLib/commit/56261fc590831eca1cdafecc9ce967265d9d2fa6))
* create event manager
  interface ([65af17a](https://github.com/AirshipCraft/AC-CommonLib/commit/65af17aacf69e67db1792c80bf2aa13fa6ebb466))
* create generic DAO
  interface ([9ff49b7](https://github.com/AirshipCraft/AC-CommonLib/commit/9ff49b791c0e8111e64be401a431ce92a1f3fdb6))
* create season
  manager ([41a230c](https://github.com/AirshipCraft/AC-CommonLib/commit/41a230c9401aba760ab6483f9fcb807157debfd6))
* create SQL connection manager
  class ([9855a18](https://github.com/AirshipCraft/AC-CommonLib/commit/9855a184a1f404c2e19787e38f36abd20c18ec98))
* create user dao
  interface ([0d1876e](https://github.com/AirshipCraft/AC-CommonLib/commit/0d1876ef5354b7a60c75e7bfd8bd00a78856e976))
* create user model for storage
  container ([a1f6582](https://github.com/AirshipCraft/AC-CommonLib/commit/a1f658239f757e39b401cfab0f2882c31a301512))
* ICalendarManager
  interface ([35f3892](https://github.com/AirshipCraft/AC-CommonLib/commit/35f38929277b09e26c6473316557bd58ed5e9354))
* new season manager
  interface ([09407df](https://github.com/AirshipCraft/AC-CommonLib/commit/09407df22ea20d30ee16f2782d315825d65c7b4f))
* SQL user DAO ([bf338b1](https://github.com/AirshipCraft/AC-CommonLib/commit/bf338b11bd70941a7e5f888d5bd0dd8625d14d67))

## [1.4.12](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.11...v1.4.12) (2023-12-25)

### Bug Fixes

* fix GH pages
  workflow ([815d127](https://github.com/AirshipCraft/AC-CommonLib/commit/815d1277103331da4c06abe81e1833be63102f83))

## [1.4.11](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.10...v1.4.11) (2023-12-25)

### Bug Fixes

* fix maven publish
  workflow ([b44f285](https://github.com/AirshipCraft/AC-CommonLib/commit/b44f28566fc5a0185633259898a8a78d0db99ea0))

## [1.4.10](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.9...v1.4.10) (2023-12-25)

### Bug Fixes

* fix incorrect path to
  secrets ([eb4239f](https://github.com/AirshipCraft/AC-CommonLib/commit/eb4239f8534b10f501042ec8d96cfa6f0981438a))

## [1.4.9](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.8...v1.4.9) (2023-12-25)

### Bug Fixes

* fix incorrect path to
  script ([cdcb5a7](https://github.com/AirshipCraft/AC-CommonLib/commit/cdcb5a78bfc7c98fdbf900cb33512c64e5907431))
* reformat
  code ([067b480](https://github.com/AirshipCraft/AC-CommonLib/commit/067b480d77816eb4fda33a82b3486e13998df8a3))

## [1.4.8](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.7...v1.4.8) (2023-12-25)

### Bug Fixes

* fix javadoc comment errors (
  hopefully) ([dc66542](https://github.com/AirshipCraft/AC-CommonLib/commit/dc665422735a2529b2828f55555a6903827a0e54))

## [1.4.7](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.6...v1.4.7) (2023-12-25)

### Bug Fixes

* fix incorrect javadoc
  tag ([4412f5b](https://github.com/AirshipCraft/AC-CommonLib/commit/4412f5bd28fdda6dd9c1c37d011e28e9d22a5bc2))

## [1.4.6](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.5...v1.4.6) (2023-12-25)

### Bug Fixes

* should finally fix the workflow not
  triggering ([00cc9ef](https://github.com/AirshipCraft/AC-CommonLib/commit/00cc9efd54e1384f5fc5e6c8ec4aac4ff05b14af))

## [1.4.5](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.4...v1.4.5) (2023-12-25)

### Bug Fixes

* fix incorrect path for
  script ([07b9394](https://github.com/AirshipCraft/AC-CommonLib/commit/07b939489f06295a44b12c6f331779909f1fd1aa))

## [1.4.4](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.3...v1.4.4) (2023-12-25)

### Bug Fixes

* update ci
  workflows ([3346781](https://github.com/AirshipCraft/AC-CommonLib/commit/3346781700dca52a5f454528cdd8d2adfd3ec7f4))

## [1.4.3](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.2...v1.4.3) (2023-12-25)

### Bug Fixes

* one more time for good
  luck ([7ba4fd8](https://github.com/AirshipCraft/AC-CommonLib/commit/7ba4fd89a8e7e739cce9161be11e9dfdfbbacb18))

## [1.4.2](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.1...v1.4.2) (2023-12-25)

### Bug Fixes

* once again fixing
  workflows ([c008427](https://github.com/AirshipCraft/AC-CommonLib/commit/c008427ada3db8b47001c8dba16e542cfe3e17cb))

## [1.4.1](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.4.0...v1.4.1) (2023-12-25)

### Bug Fixes

* fix 2
  scripts ([b76c972](https://github.com/AirshipCraft/AC-CommonLib/commit/b76c972bed7dea6142548b70493bc273c2b2a5ec))
* fix changelog
  workflow ([4c58d39](https://github.com/AirshipCraft/AC-CommonLib/commit/4c58d3921c2def31f9cbcb3c710a0f408309d351))
* update
  workflows ([bce1e95](https://github.com/AirshipCraft/AC-CommonLib/commit/bce1e9576b4009280621a1ce86d9ffd3b314d2e9))
* update workflows
  again ([915a8bd](https://github.com/AirshipCraft/AC-CommonLib/commit/915a8bdc8208f5259b1a2764d2de1b5b89a81623))

# [1.4.0](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.3.1...v1.4.0) (2023-12-25)

### Bug Fixes

* fix abstract method
  header ([90431cd](https://github.com/AirshipCraft/AC-CommonLib/commit/90431cd52919545530c1247c40915bda3c826695))
* made fields
  final ([0343e3e](https://github.com/AirshipCraft/AC-CommonLib/commit/0343e3e25430e41c0e69c402ba05e336620084ad))
* re-add redundant methods for checking stripped/unstripped
  logs ([44a82d2](https://github.com/AirshipCraft/AC-CommonLib/commit/44a82d2acc3ca29218f5848b67d271aad3e3b731))

### Features

* add vector math
  util ([ea83769](https://github.com/AirshipCraft/AC-CommonLib/commit/ea83769effb661c1f9aac47c95ddf5a43263245d))
* added new methods to raycast
  util ([d43afdc](https://github.com/AirshipCraft/AC-CommonLib/commit/d43afdcfecb1f469af29ff46119bed573cc9368e))
* create random math
  util ([40c33ed](https://github.com/AirshipCraft/AC-CommonLib/commit/40c33eda0bbf95a019946b4e282519738816e22d))
* pair util ([83e17f8](https://github.com/AirshipCraft/AC-CommonLib/commit/83e17f8f50ae727c3323fb621492d5e582e63912))

## [1.3.1](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.3.0...v1.3.1) (2023-12-21)

### Bug Fixes

* change maven host
  repo ([0952661](https://github.com/AirshipCraft/AC-CommonLib/commit/09526616c59e437d7ec03c8beed96b02c1baff86))
* fix maven deploy workflow to accurately reflect these
  changes ([3e7ed93](https://github.com/AirshipCraft/AC-CommonLib/commit/3e7ed93a3bdf5ee03ffba8a8d63f902f65c7361b))
* moved repo to
  subdomain ([90787a6](https://github.com/AirshipCraft/AC-CommonLib/commit/90787a6a9e792070ef711b1a1b7848c62b2b60d6))

# [1.3.0](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.2.0...v1.3.0) (2023-12-14)

### Features

* add methods for creating or loading default
  config ([e958655](https://github.com/AirshipCraft/AC-CommonLib/commit/e9586551be2964dea59a1bee19e10ab81d6baba2))
* configoption
  annotation ([b45863a](https://github.com/AirshipCraft/AC-CommonLib/commit/b45863a13b9385fb5794478818a27f4e125ecba5))
* create configuration
  manager ([9318cb9](https://github.com/AirshipCraft/AC-CommonLib/commit/9318cb983941a50aa1c03340720e02781925f163))
* work on repairConfig()
  method ([97b5a57](https://github.com/AirshipCraft/AC-CommonLib/commit/97b5a57891547ee6e34f00c2fa800017f37bb6ff))

# [1.2.0](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.13...v1.2.0) (2023-12-09)

### Bug Fixes

* add warped and crimson
  planks ([4cac897](https://github.com/AirshipCraft/AC-CommonLib/commit/4cac897dee1c0e436b894017a13df9c13d5ef96e))
* remove useless
  blocktype ([8f511c7](https://github.com/AirshipCraft/AC-CommonLib/commit/8f511c73bbdfbeea593b0a02198976b680d53f6a))

### Features

* add block
  utils ([e4bfb9b](https://github.com/AirshipCraft/AC-CommonLib/commit/e4bfb9bef9deb4ce450a6f621639fbe8d5c70412))
* add checks for signs (wall and
  non) ([7abcd01](https://github.com/AirshipCraft/AC-CommonLib/commit/7abcd01cfcd415264fbe6b5172a6b725f4412cb6))
* add methods for checking candle
  blocktype ([fe0be82](https://github.com/AirshipCraft/AC-CommonLib/commit/fe0be8253f4aebbb2a2a0274f8ba3ebc0e96080d))
* add methods for wood and
  leaves ([b9b0720](https://github.com/AirshipCraft/AC-CommonLib/commit/b9b0720436cc527276faca0f7980e9454d0470c4))
* deprecated seperate methods for stripped/unstripped
  variants ([11c7355](https://github.com/AirshipCraft/AC-CommonLib/commit/11c7355de8f5cc01b31e5c5dcea23dd286655c33))

## [1.1.13](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.12...v1.1.13) (2023-11-24)

### Bug Fixes

* generate javadocs AFTER correct
  versioning ([bf4f17e](https://github.com/AirshipCraft/AC-CommonLib/commit/bf4f17e0c06d6d5c821aa7d7dc4ee52ed818d7d6))

## [1.1.12](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.11...v1.1.12) (2023-11-24)

### Bug Fixes

* fix incorrect syntax in workflow causing it to
  fail ([b966923](https://github.com/AirshipCraft/AC-CommonLib/commit/b966923c89b8198d9ac3f2ba4a83e34bce33971c))

## [1.1.11](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.10...v1.1.11) (2023-11-24)

### Bug Fixes

* fix changing of pom
  version ([efbcde1](https://github.com/AirshipCraft/AC-CommonLib/commit/efbcde122e6e79a45ab4dfdacc7eecb8c4cb8dc5))
* fix pom to show correct
  version ([c538146](https://github.com/AirshipCraft/AC-CommonLib/commit/c538146533d3bc5ac822d7f3c8bbb5a0fefdd734))
* more workflow
  changes ([e7d0087](https://github.com/AirshipCraft/AC-CommonLib/commit/e7d0087724f09adc0608e1a47d620a580b8303db))

## [1.1.10](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.9...v1.1.10) (2023-11-24)

### Bug Fixes

* use github token and change permission
  scope ([e087f3e](https://github.com/AirshipCraft/AC-CommonLib/commit/e087f3e37ff238850ffd47e0838c27a79b332272))

## [1.1.9](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.8...v1.1.9) (2023-11-24)

### Bug Fixes

* small particle util
  class ([5c1bb4c](https://github.com/AirshipCraft/AC-CommonLib/commit/5c1bb4cb65128ad6b9edab99dcbccdff7d7f8cd9))

## [1.1.8](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.7...v1.1.8) (2023-11-24)

### Bug Fixes

* specifying different access token this
  time ([d78aa5e](https://github.com/AirshipCraft/AC-CommonLib/commit/d78aa5e609b05bb50ec2804bfe01d5297af9069f))

## [1.1.7](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.6...v1.1.7) (2023-11-24)

### Bug Fixes

* small fix to deploy
  workflow ([4d2eee1](https://github.com/AirshipCraft/AC-CommonLib/commit/4d2eee1db36229eaa47993b4bd7484461a12dc6d))

## [1.1.6](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.5...v1.1.6) (2023-11-24)

### Bug Fixes

* update maven build
  workflow ([f16383e](https://github.com/AirshipCraft/AC-CommonLib/commit/f16383e2821401876e4d692bf2bd17f68709bf50))

## [1.1.5](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.4...v1.1.5) (2023-11-24)

### Bug Fixes

* more workflow
  fixes ([14d0de5](https://github.com/AirshipCraft/AC-CommonLib/commit/14d0de53bc7dcfe7aa88f0433addb787c70bb21b))

## [1.1.4](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.3...v1.1.4) (2023-11-24)

### Bug Fixes

* Update
  maven-publish.yml ([cd75a1e](https://github.com/AirshipCraft/AC-CommonLib/commit/cd75a1e1153cf2b2260cff301b9a6c887ba5e1da))

## [1.1.3](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.2...v1.1.3) (2023-11-24)

### Bug Fixes

* fix deploy
  issue ([cd8d87f](https://github.com/AirshipCraft/AC-CommonLib/commit/cd8d87f6abc6a58f6ef79e69845162593205472c))

## [1.1.2](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.1...v1.1.2) (2023-11-24)

### Bug Fixes

* really horrible workflow
  issue ([d9faeea](https://github.com/AirshipCraft/AC-CommonLib/commit/d9faeea54fcb4a217ed49ea2ccfded73382fd595))

## [1.1.1](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.1.0...v1.1.1) (2023-11-24)

### Bug Fixes

* workflow fix ([047d553](https://github.com/AirshipCraft/AC-CommonLib/commit/047d553f46ed00550b464067b68485e781831125))

# [1.1.0](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.0.3...v1.1.0) (2023-11-24)

### Bug Fixes

* fix constructor to add inventory
  type ([563ad1d](https://github.com/AirshipCraft/AC-CommonLib/commit/563ad1d1e7581bff65b446c0f5ce6b12b185daf8))
* improve ui designer
  class ([5872c01](https://github.com/AirshipCraft/AC-CommonLib/commit/5872c01a20541fcfc1f954362b6f1f946cdd5daa))
* pom changes ([0e2aae5](https://github.com/AirshipCraft/AC-CommonLib/commit/0e2aae5c833f6e9101fe93f6d40676f5074ea212))
* re-add missing
  license ([793b2f3](https://github.com/AirshipCraft/AC-CommonLib/commit/793b2f3bfa02ce7c4692ad68e011d76e3b490111))

### Features

* creating
  package-infos ([13999fe](https://github.com/AirshipCraft/AC-CommonLib/commit/13999fe6b3b5f23f1350fd19c8494a94e8bc6309))

## [1.0.3](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.0.2...v1.0.3) (2023-11-21)

### Bug Fixes

* add helper methods to inv click
  listener ([27b50da](https://github.com/AirshipCraft/AC-CommonLib/commit/27b50da49e412d22a011af7ebf5f52fed061fd96))
* add helper methods to
  sidebar ([9763d13](https://github.com/AirshipCraft/AC-CommonLib/commit/9763d13a6f2db935a9fb80ddf08e996491c40917))
* add isUI
  method ([f7857e5](https://github.com/AirshipCraft/AC-CommonLib/commit/f7857e5d1aad1fb522bc172db17f725569e163ea))
* add javadoc comments and helper methods to
  bossbar ([c2922df](https://github.com/AirshipCraft/AC-CommonLib/commit/c2922df86ffd06dd516b5cd2c40f110dbe67151e))
* workflow errors with
  javadocs ([8658992](https://github.com/AirshipCraft/AC-CommonLib/commit/8658992db3b07e67e68e8df96ab3c095faefc300))

## [1.0.2](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.0.1...v1.0.2) (2023-11-21)

### Bug Fixes

* lang version in
  workflows ([2f5d4f3](https://github.com/AirshipCraft/AC-CommonLib/commit/2f5d4f32ac55fb6f400d141afef86e6af1cfaed6))

## [1.0.1](https://github.com/AirshipCraft/AC-CommonLib/compare/v1.0.0...v1.0.1) (2023-11-21)

### Bug Fixes

* add workflow
  scripts ([db97801](https://github.com/AirshipCraft/AC-CommonLib/commit/db97801b37461efce0f2c276dd84401d63aab456))

# 1.0.0 (2023-11-21)

### Bug Fixes

* corrected method to get
  instance ([ac2f69a](https://github.com/AirshipCraft/AC-CommonLib/commit/ac2f69a6c5bbc6a68bf4415ab1f5c5db291119d3))
* create register events
  method ([04c7345](https://github.com/AirshipCraft/AC-CommonLib/commit/04c73451a55fd3b101ffd2a3ae7f1afa136468f3))
* create release
  config ([396ae7c](https://github.com/AirshipCraft/AC-CommonLib/commit/396ae7c3c16e0845c4fe51ca5ccb2fba6c8ca1c4))
* fix ACRPlugin to use CommonLib
  pattern ([9bbff17](https://github.com/AirshipCraft/AC-CommonLib/commit/9bbff17f52475ac97d0a0620c2536db3f9eeac12))
* fix logging in UIDesigner
  class ([04aeb51](https://github.com/AirshipCraft/AC-CommonLib/commit/04aeb5138f5181a7fd687a9193f599921a2b67d1))
* get rid of deprecated
  classes ([8a4c5ea](https://github.com/AirshipCraft/AC-CommonLib/commit/8a4c5ea72fd369d5027cce7a7b86eff2200b21a9))
* improve particleeffect
  class ([6ccb3e6](https://github.com/AirshipCraft/AC-CommonLib/commit/6ccb3e667606bd9bf5706a2b6a9070988c3fcd65))
* make commonlib class be a singleton
  pattern ([b760f8b](https://github.com/AirshipCraft/AC-CommonLib/commit/b760f8b6a9e37cde4ad61b50f3e6db917861fb86))
* revamp UI
  class ([0dfd38f](https://github.com/AirshipCraft/AC-CommonLib/commit/0dfd38f215629f5846ae6f873d292ca0aaae693b))
* update BelowName
  obj ([8097ddf](https://github.com/AirshipCraft/AC-CommonLib/commit/8097ddf355967223b84d89f65ac924f5096363e5))
* update commonlib to add pref
  manager ([9dc2f43](https://github.com/AirshipCraft/AC-CommonLib/commit/9dc2f4399111fccb9590b68514c11fed70dca8ed))

### Features

* add a team manager
  class ([311876b](https://github.com/AirshipCraft/AC-CommonLib/commit/311876b1d7fc8f910973c9fc995299d06cc57041))
* add ITeam
  interface ([f3dcec0](https://github.com/AirshipCraft/AC-CommonLib/commit/f3dcec08c2961d8188520fea9f20af9e97288b81))
* add key-value tick cooldown
  handler ([bd898b3](https://github.com/AirshipCraft/AC-CommonLib/commit/bd898b32c9b510ffa1a38949343659f74a825171))
* add method for getting instance of
  teammanager ([f92ddce](https://github.com/AirshipCraft/AC-CommonLib/commit/f92ddce7491d94bca35a0484e3dacdd6b7327279))
* add method to
  getPreference ([0180972](https://github.com/AirshipCraft/AC-CommonLib/commit/0180972c6d8ee716e81ee9a9cd8b23e8f4240f70))
* add more logging
  methods ([679382d](https://github.com/AirshipCraft/AC-CommonLib/commit/679382d9231c5cc0f31a67fb5f414e18d84bc954))
* add playerpref
  annotation ([c0b40e5](https://github.com/AirshipCraft/AC-CommonLib/commit/c0b40e54732209874660dea37d8d45600acdc8bb))
* add preference
  command ([2d085c1](https://github.com/AirshipCraft/AC-CommonLib/commit/2d085c176cc019dea9d2f82fef420c1d06851566))
* add steps to set up
  logging ([4d1c5d3](https://github.com/AirshipCraft/AC-CommonLib/commit/4d1c5d32aa3232fac251b03a40d248e93ffa8d03))
* add utility methods to pref
  manager ([067fe40](https://github.com/AirshipCraft/AC-CommonLib/commit/067fe405a34df44ed1be03596688b8793b10b645))
* add workflow for deploying to
  maven ([02ecfb6](https://github.com/AirshipCraft/AC-CommonLib/commit/02ecfb64c62822112bc96f2bb1c15baf44a835f9))
* creat player preference
  object ([f9ff325](https://github.com/AirshipCraft/AC-CommonLib/commit/f9ff32545819f618051becbdac574a1ed3470810))
* create a progression curve
  algorithim ([8589eaf](https://github.com/AirshipCraft/AC-CommonLib/commit/8589eaf137e6b023cb290f2f8523bea84680d240))
* create abstract gui
  class ([0acca81](https://github.com/AirshipCraft/AC-CommonLib/commit/0acca81deec5a937b08d579c5d85c2b0dc3c37a9))
* create gui builder
  class ([f72158b](https://github.com/AirshipCraft/AC-CommonLib/commit/f72158bb08bdb0ff300c7baa1d5f0dcbc45c287f))
* create gui
  manager ([9836df7](https://github.com/AirshipCraft/AC-CommonLib/commit/9836df7c95d3595b7bc1b9e8ffc85fa6cced3066))
* create player preference
  interface ([0358a7c](https://github.com/AirshipCraft/AC-CommonLib/commit/0358a7c4cb55e06ac96b65f43594ef0bab994459))
* create preference
  processor ([d0895d8](https://github.com/AirshipCraft/AC-CommonLib/commit/d0895d8da6c4ac145c0b2522f73b560af5176a90))
* create
  preferencesmanager ([1e6e034](https://github.com/AirshipCraft/AC-CommonLib/commit/1e6e034fb978926015bdebddb8ac38912da6ab38))
