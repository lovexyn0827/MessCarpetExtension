# MessMod

![](https://raw.githubusercontent.com/wiki/lovexyn0827/MessMod/media/MessMod.png)

һ�������ṩ������������������һЩ��ǿ���ܵ�Minecraft Mod  

��������������˵�����Mod�����˺ܶ෽��Ĺ��ܣ�������ƣ���Ϣ��ʾ�����Ը����Լ�һЩBug�޸��ȣ�����Щ�������д�ģ��ǳ��ң�����������Ҳ���ҡ�

�������ԣ� 
[English](/README.md)  

## ����

1. Fabric Loader 0.7.4+  
2. gnembon�ĵ�̺Mod��fabric-carpet�������ֹ�����Ҫ������ǿ���Ƽ��������Ǳ��裩  
3. Minecraft 1.16.4��1.16.5  
4. Minecraft 1.16.x ��Ҫ�����ж��� 

## ����

���������ü�������ס����������Ϊ��ѡ���֡�

##### `/ensure <pos>`

��ȡ`<pos>`���ķ����Լ�����ʵ�壨���У���Ϣ������ȷ��ĳ�������Ƿ������Ⱦ����������鷽�顣

##### `/entityconfig <targets> enableStepHeight|disableStepHeight`

������ֹ`<targets>`ָ����ʵ��ֱ�����Ϸ��顣  

##### `/entityconfig localPlayer enableStepHeight|disableStepHeight`

������ֹ�ͻ������ֱ�����Ϸ��顣  

##### `/entityfield <target> get <fieldName>`

��ȡ����targetָ����ʵ��Ķ�������Ϊ`<fieldName`>���ֶΡ����û�м��ػ���ӳ����˴���������������field_827��intermediary names����Ҫ�˹������Ա���⡣  

##### `/entityfield <target> listAvailableFields`

��ȡ����`<target>`ָ����ʵ��Ķ����������ֶε������б�  

##### `/entityfield <target> set <fieldName> <newValue>`

������`<target>`ָ����ʵ��Ķ�������Ϊ`<fieldName>`���ֶε�ֵ��Ϊ`<newValue>`��֧�ֵ����ͣ�int��float��double��boolean�����ڿ�������ʧ�ܣ���Vec3d��  

##### `/entitylog sub <target>`

��ʼ����`<target>`��ָ����ʵ�岢����ÿһ��Ϸ�̵�������Motion�Լ������ӵ��ֶδ洢��һ��CSV����ļ��С�ע�ⲿ�ּ�¼�ᱻ��ʱ���浽һ������������������д�뵽�ļ����У�����ʹ��`/entitylog flush`����Щ��������д�롣

��¼�ļ��ᱻ���浽�����ļ����е�`entitylog`�ļ�����

##### `/entitylog unsub <target>`

ֹͣ����`<target>`��ָ����ʵ�岢������������������д�뵽�ļ�����ֹͣ�����¿�ʼ��һ��ʵ����ӻ����´���һ����־�ļ����Ǽ���ԭ�ļ���

##### `/entitylog listenField <type> <field> [<name> [<path>]]`

����ָ����ʵ���һ���ֶΣ���ָ���Զ������ƺ�AccessingPath��`<type>`��ʱ���Ὣ��ָ���Ӱ�췶Χ���Ƶ����Ӧ��ʵ�����͡�

ִ�и�ָ���ʹ��ǰ���ж��ض�ʵ��ļ������¿�ʼ��

##### `/entitylog flush`

������������������д�뵽�ļ�����ֹͣ���ӡ�

##### `/entitylog stopListenField <field>`

ֹͣ����һ���ֶΡ�

##### `/entitylog listListenedFields`

�г��������������ֶΡ�

##### `/entitylog autoSub <entityType>`

��ʼ�Զ�����ĳһ���͵�ʵ�塣

##### `/entitylog stopAutoSub <entityType>`

ֹͣ�Զ�����ĳһ���͵�ʵ�塣

##### `/entitylog autoSubName <name>`

��ʼ�Զ�������Ϊ`<name>`��ָ�����Ƶ�ʵ�塣

##### `/entitylog stopAutoSubName <name>`

ֹͣ�Զ�������Ϊ`<name>`��ָ�����Ƶ�ʵ�塣

##### `/entitysidebar add <target> <field> <name> [<whereToUpdate> [<path>]]`

��ʵ����Ϣ�������������ʵ��`<target>`�ṩ��һ����Ϣ������ָ������ʱ����AccessingPath��֧�ֵĸ���ʱ����

- WEATHER_CYCLE��������ʱ���������ʱ

- CHUNK���������ж�غ�������������ʱ

- SCHEDULED_TICK���ƻ����������ʱ

- VILLAGE��Ϯ���������ʱ

- BLOCK_EVENT�������¼��������ʱ

- ENTITY��ʵ���������ʱ

- TILE_ENTITY������ʵ���������ʱ

- SERVER_TASKS�����������첽�¼��������ʱ

##### `/entitysidebar remove <name>`

��ʵ����Ϣ��������Ƴ�һ�С�

##### `/explode <pos> <power> [<fire>]`

��`<pos>`������һ������Ϊ`<power>`�ı�ը������ָ��`<fire>`Ϊ`true`ʹ��ը���ɻ��档��ը�������������ⵥ���ȸ��������������������NaN��

##### `/hud subField target <entityType> <field> [<name> [<path>]]`

��Ŀ��ʵ����ϢHUD����һ���������ֶΣ���ָ���Զ������ƺ�AccessingPath��ʵ�����Ͳ���Ŀǰ�������ṩ��field�����Ľ���ͻ�ȡ��Ӧ�ֶΣ�Fieldʵ���������������á�

##### `/hud subField client|server <field> [<name> [<path>]]`

��ͻ��˻����������ϢHUD����һ���������ֶΣ���ָ���Զ������ƺ�AccessingPath��

##### `/hud unsubField target|client|server <name>`

��һ��HUD���Ƴ�һ���ֶμ����

##### `/hud setHudTarget <profile>`

���ö��˻����·���������ϢHUD��Ŀ��ʵ����ϢHUD�����ݻ�ȡ�����õ���ҡ�

##### `/lag nanoseconds [<thread>]`

����Ϸ��ĳһ�߳�`<thread>`����`<nonoseconds>`���룬������ĳЩ���ԡ���δָ���߳�����������̡߳�

##### `/messcfg`

��ȡ��ǰMod�汾�����������  

##### `/messcfg reloadConfig`

��`mcwmem.prop`��ȡ���µ�������Ϣ��

##### `/messcfg setGlobal <option> <value>`

��ѡ��`<option>`�Ķ����´򿪵Ĵ浵��Ĭ��ֵ����ǰ�浵�ľֲ�ȡֵ��Ϊ`<value>`��

##### `/messcfg <option>`

�鿴ѡ��`<option>`��ֵ�Լ���ذ�����Ϣ��

##### `/messcfg <option> <value>`

�ڵ�ǰ�浵��Χ�ڽ�ѡ��`<option>`��ֵ��Ϊ`<value>`������ĵ�����һ�ڡ�

##### `/modify <targets> <key> <val>`

�޸�`<targets>`ָ����ʵ������ԣ��ǲ���`/entityfield`�ļ���÷���  

##### `/modify <target> remove`

�Ƴ�`<targets>`ָ����ʵ�塣  

##### `/moventity <targets> <delta> entity self|piston|shulkerBox|player|shulker`

ʹ��`Entity.move()`�ƶ�`<targets>`ָ����ʵ�壬������`<delta>`ָ����ͨ��ѡ��self����Ϊ���һ��������ִ����ɺ����ʵ��λ�ơ�  

##### `/moventity <targets> <delta> projectile`

ʹ�õ������ƶ��ķ�ʽ�ƶ�`<target>`ָ����ʵ�壬������`<delta>`ָ����Ŀǰ�ƺ�����һЩBug��  

##### `/namentity <entities> <name>`

Ϊѡ����ʵ�����������

##### `/poi get <pos>`

ȡ`<pos>`����POI����Ȥ�㣩�� 

##### `/poi getDistanceToNearestOccupied <pos>`

��������Ĵ�����վ����ĳ�ֳ߶��µľ��루������������Ϊ��λ�������پ��룩��

##### `/poi scan <center> <radius> <type>`

Ѱ����`<center>`Ϊ���ģ��뾶Ϊ`<radius>`����������Ϊ`<type>`��POI����������ڷ������ꡣ

##### `/poi scanCobic <corner1> <corner2> <type>`

Ѱ�Ҹ�������������䳤��������������Ϊ`<type>`��POI��

##### `/poi set <pos> <type> <replace>`

��`<pos>`����Ϊ`<type>`ָ����POI��������POI��`<replace>`Ϊfalse�򲻻����á� 

##### `/raycast blocks <from> <to> [visual]`

����`<from>`��`<to>`�������߶��Ƿ񱻷����赲����ʽ�����ڵ��������ײ���ͱ�ը�Ӵ��ʼ����е�raycast��ִ�к�������������з������꣨�����ظ���������������赲������赲�߶εķ�������꼰���赲�߶ε����Լ���������꣬�������Missed�����������visual�������̻ᱻ���ӻ��������ܼ��ķ���������ʾΪǳ��ɫ���赲�߶εķ�����ײ��ᱻ��ʾ��ɫ����������ᱻ��ʾΪ��ɫ���߶εı��赲ǰ��Ĳ��ֱַ�Ϊ��ɫ�ͺ�ɫ��

![](https://raw.githubusercontent.com/wiki/lovexyn0827/MessMod/media/raycast-block.png)

##### `/raycast entities <from> <to> <expand> <excludeSender> [visual]`

���`<from>`��`<to>`�������߶��Ƿ񱻷����赲����ʽ�����ڵ��������ײ��顣ִ�к�����赲�߶ε�ʵ�����ͣ����Զ������ƣ�����ID����ײ�����꣬�������Missed��`<expand>`����Ӱ���ȡʵ��ķ�Χ��ģ�ⵯ�������ײ���ʱ����ָ��Ϊ��������ײ���ȵ�һ�����1��

##### `/repeat <times> <feedbacks> ...`

�ظ�ִ��ĳ������`<times>`�Σ�������`<feedback>`��ָ���Ƿ��������������

##### `/ride <passengers> <vehicle> <force>`

��`<passengers>`ָ����ʵ�����`<vehicle>`��

##### `/rng world setSeed <seed>`

���õ�ǰά�ȵ�RNG�������������������Ϊ`<seed>`��  

##### `/rng world next int <bounds>`

ȡ��ǰά�ȵ���������������ɵ���һ��������[0,`<bounds>`)�ڵ�������

##### `/rng world next int|float|double|boolean`

ȡ��ǰά�ȵ���������������ɵ���һ��������� 

##### `/rng <target> ...`

��ǰ�����������ƣ�ֻ��RNG����`<target>`ָ����ʵ����Ǹ�ά�ȡ�  

##### `/setexplosionblock <blockState> [<fireState>]`

ʹ��ը���ƻ�����ʱ����`<blockState>`ָ���ķ�������ǿ�����������ը��Ҫ���õĻ�ȫ���滻��`<fireState>`ָ���ķ��顣  

##### `/tileentity get <pos>`

ȡ`<pos>`���ķ���ʵ����Ϣ��  

##### `/tileentity set <pos> <type> [<tag>]`

����`<pos>`����Ϊ`<type>`ָ���ķ���ʵ�塣����ָ��`<tag>`��Ϊ����ʵ��ĳ�ʼ���ݣ���ʱ����`<pos>`���ķ��鲻֧�ָ÷���ʵ�壬���û�ʧ�ܣ���  

##### `/tileentity remove <pos>`

�Ƴ�`<pos>`���ķ���ʵ�塣��Ŀǰ�汾�У�����ô�����һ����Ҫ����ʵ��ķ��飬���Ƴ���ô�����ʵ��ᱻ�÷����������ã�һ��Bug����  

## ������

����ѡ���ͨ��`/messcfg`�������ã���ʽ��Ϊ`/messcfg <ѡ����> <ֵ>`��������ʵ����ײ����ʾ��ʹ��`/messcfg serverSyncedBox true`

##### `accessingPathInitStrategy`

����AccessingPath�ĳ�ʼ������

����3�ֳ�ʼ�����ԣ� 

- �ɰ汾ģʽ��ÿ��Accessing Pathֻ�ڵ�һ�α�ʹ��ʱ���г�ʼ����Ȼ����һ������ڷ��ʺ���������������� 

- ��׼ģʽ��Accessing Path���ÿ����ͬ������г�ʼ����Ȼ�����ᱻ����ֱ����Ӧ��������

- �ϸ�ģʽ��Accessing Pathÿ�α�ʹ��ʱ�������½��г�ʼ����

����ȡֵ��`LEGACY|STANDARD|STRICT`

Ĭ��ȡֵ��`STANDARD`

##### `antiHostCheating`

�Ե�����Ϸ��һ��������Ϸ�ķ������÷����ס�

����ȡֵ��true��false

Ĭ��ֵ��false

##### `attackableTnt`

TNT���Ա���ҵĹ���ɱ����

����ȡֵ��true��false

Ĭ��ֵ��false

##### `blockInfoRendererUpdateInFrozenTicks`

��Ϸ���㱻��̺����/tickָ����ͣʱ������Ϣ��Ⱦ������Ϊ��

- NORMALLY���������и���

- PAUSE����ͣ����

- NO_REMOVAL������������У����Ƴ���ͣ

����ȡֵ��`NORMALLY|PAUSE|NO_REMOVAL`

Ĭ��ֵ��`NORMALLY`

##### `blockShapeToBeRendered  `

��ѡ��`renderBlockShape`Ϊtrueʱ����Ⱦ�ķ����������͡�`COLLIDER`��Ӧ�������ײ�䣬OUTLINE��Ӧ��������Ҷ�׼ʱ��ʾ�ı߽硣

����ȡֵ��`COLLIDER|OUTLINE|RAYSAST|SIDES|VISUAL`

Ĭ��ֵ��`COLLIDER`

##### `commandExecutionRequirment`

��Mod�ж���������Ƿ���ҪOPȨ�ޡ�

����ȡֵ��true��false

Ĭ��ֵ��false

##### `craftingTableBUD`

��������̨�յ��ķ�����¡�

����ȡֵ��true��false

Ĭ��ֵ��false

##### `creativeUpwardsSpeed`

���ڴ���ģʽ��������Ϸ��У��ӣ��ٶȴ�С��

����ȡֵ��0-1024���ڵ�ʵ��

Ĭ��ֵ��0.05

##### `debugStickSkipsInvaildState`

��ֹ���԰�������������Ƿ�״̬��Ŀǰ���ѡ����һЩ����²�������������������������shape����ʱ�Կ��Խ���������Ƿ�״̬ʹ����䡣

����ȡֵ��true��false

Ĭ��ֵ��false

##### `disableChunkLoadingCheckInCommands`

����ָ���жԷ����Ƿ񱻼��صļ��

����ȡֵ��true��false

Ĭ��ֵ��false

##### `disableExplosionExposureCalculation`

���ñ�ը�Ӵ��ʼ��������̶ѵ�TNT�ı�ը����ʱ�䡣���ʹ�����޷���ֹ���ʵ�屻��ըӰ�졣�ڰ�װLithium����ܲ�������������

����ȡֵ��true��false

Ĭ��ֵ��false

##### `disableProjectileRandomness`

ȡ�������������ٶȣ����ڲ��ֲ��ԣ�������Ҫ���ǹرա�

����ȡֵ��true��false

Ĭ��ֵ��false

##### `enabledTools`

���û���ù�����Ʒ�������÷������ġ�  

����ȡֵ��true��false

Ĭ��ֵ��false

##### `endEyeTeleport`

��ʹ��ĩӰ֮��ʱ����Ҵ��͵����ע�ӵĵط���

����ȡֵ��true��false

Ĭ��ֵ��false

##### `entityExplosionInfluence`

��ըӰ�쵽ʵ��ʱ�����������ʵ�屻Ӱ���������ڰ�װLithium����ܲ�������������

����ȡֵ��true��false

Ĭ��ֵ��false

##### `entityExplosionRaysVisiblity`

���û����ʵ�屬ը������Ⱦ�� 

����ȡֵ��true��false

Ĭ��ֵ��false

##### `entityExplosionRaysLifetime`

����ʵ�屬ը���ߵ���ʾʱ������λΪ�̡�

����ȡֵ������������

Ĭ��ֵ��300

##### `entityLogAutoArchiving`

�Զ��浵һ���Ự�ڲ�����ʵ�����ݼ�¼��

����ȡֵ��true��false

Ĭ��ֵ��false

##### `getEntityRangeExpansion`

<font color=red>[TODO]</font>��ԭ���getEntity()�����У�ֻ�������AABB���б�ѩ�����С��2m�������е�ʵ��ᱻ�������� ����ʱ��ᵼ��һЩ�ϴ�ʵ�������Ľ����������⡣����ѡ���Ϊһ���ϴ�ֵ�����޸���һBug��

����ȡֵ��true��false

Ĭ��ֵ��false

##### `hudAlignMode`

����ʵ����ϢHUD��λ�á�

����ȡֵ��BOTTOM_LEFT|BOTTOM_RIGHT|TOP_LEFT|TOP_RIGHT

Ĭ��ֵ��TOP_RIGHT

##### `hudStyles`

HUD����Ⱦ��ʽ���������������������־��

- B����Ⱦ����

- L��������б��Ⲣ�Ҷ�������

- R�����б����Ϊ��ɫ

����ȡֵ�������ַ���

Ĭ��ֵ��(BL)^2/(mR)

##### `hudTextSize`

����HUD�����С��

����ȡֵ��0-10������ʵ��

Ĭ��ֵ��1

##### `language`

��Mod����Ҫ���ԡ�

����ȡֵ��`zh_cn|en_us|zh_cn_FORCELOAD|en_us_FORCELOAD|-FOLLOW_SYSTEM_SETTINGS-`

Ĭ��ֵ��`-FOLLOW_SYSTEM_SETTINGS-`������ϵͳ���ã�

##### `maxClientTicksPerFrame`

FPS����20ʱÿ֡�ͻ��˿������е���Ϸ��������������Ϊһ����Сֵ�����޸���FPSʱ�޷��л�����״̬��Bug�� 

����ȡֵ������������

Ĭ��ֵ��10

##### `maxEndEyeTpRadius`

`endEyeTeleport`���ܵ���Զ���;��롣

����ȡֵ��������ʵ��

Ĭ��ֵ��180

##### `mobFastKill`

�����ﱻ/kill ...ɱ��ʱ�Ƿ�ֱ���Ƴ����ǳ����˺���

����ȡֵ��true��false

Ĭ��ֵ��false

##### `projectileChunkLoading`

����������������������Զ��������飬������������ʱ���ܻ�����������ע��ù��ܿ��ܻ��ڵ������Լ��ߵ��ٶȷ���ʱ��ɼ���Ŀ��١�

����ȡֵ��true��false

Ĭ��ֵ��false

##### `projectileChunkLoadingPermanence`

��������`projectileChunkLoading`����ʱ���ü������顣

����ȡֵ��true��false

Ĭ��ֵ��false

##### `projectileChunkLoadingRange`

����`projectileChunkLoading`�����д�����ǿ���ط�Χ�İ뾶��

����ȡֵ������������������͸�����

Ĭ��ֵ��3

##### `railNoAutoConnection`

��ֹ�������̬���ٽ�������Ӱ�졣

����ȡֵ��true��false

Ĭ��ֵ��false

##### `rejectChunkTicket`

��ֹ���鱻��ĳ����ʽ���ء�

����ȡֵ��`[]`�����б���һ��`a,b,c`��ʽ���б�����һ�������������ƱID

Ĭ��ֵ��`[]`

##### `renderBlockShape`

����������Ⱦ��

����ȡֵ��true��false

Ĭ��ֵ��false

##### `renderFluidShape`

�����������߶Ⱥ�������������Ⱦ��

����ȡֵ��true��false

Ĭ��ֵ��false

##### `renderRedstoneGateInfo`

��ʾ���ע�ӵĺ�ʯ�м������ʯ�Ƚ������źŵȼ���

����ȡֵ��true��false

Ĭ��ֵ��false

##### `serverSyncedBox`

�������ײ����ʾ��  

����ȡֵ��true��false

Ĭ��ֵ��false

##### `serverSyncedBoxRenderRange`

�������ײ�����Ⱦ��Χ����mΪ��λ���б�ѩ����룩���κηǸ�ֵ�ᱻ��Ϊ�������

����ȡֵ������ʵ����������͸�����

Ĭ��ֵ��-1

##### `skippedGenerationStages`

����һЩ�������ɽ׶Ρ�

����ȡֵ��`[]`�����б���һ��`a,b,c`��ʽ���б�����һЩChunkStatus��`...`��

Ĭ��ֵ��`[]`

##### `skipUnloadedChunkInRaycasting`

����δ���������е�Ǳ����ײ�����Խ���Զ����raycast�Ŀ��١�

����ȡֵ��true��false

Ĭ��ֵ��false

##### `stableHudLocation`

ʹHUD��λ�������ݳ���Ƶ���仯�Ǹ����ȶ���

����ȡֵ��true��false

Ĭ��ֵ��false

##### `strictAccessingPathParsing`

<font color=red>[TODO]</font>���ͽ׶��ϸ���Accessing Path��������һ���ܿ�ʹAccessing Path�ڶ��Ļ����б��ָ��ȶ���

����ȡֵ��true��false

Ĭ��ֵ��false

##### `superSuperSecretSetting`

<font color=red>[TODO]</font>wlujkgfdhlqcmyfdhj...ǧ��Ҫ�򿪣�

����ȡֵ��true��false

Ĭ��ֵ��false

##### `tntChunkLoading`

����TNTʵ����������������Զ��������顣ע��ù��ܿ��ܻ���TNT�Լ��ߵ��ٶȷ���ʱ��ɼ���Ŀ��١�

����ȡֵ��true��false

Ĭ��ֵ��false

##### `tntChunkLoadingPermanence`

TNT��`tntChunkLoading`����ʱ���ü������顣

����ȡֵ��true��false

Ĭ��ֵ��false

##### `tntChunkLoadingRange`

����`tntChunkLoading`�����д�����ǿ���ط�Χ�İ뾶��

����ȡֵ������������������͸�����

Ĭ��ֵ��3

##### `vanillaDebugRenderers`

����һЩԭ�������Ⱦ����

����ȡֵ��`[]`�����б���һ��`a,b,c`��ʽ���б�����һ��������Ⱦ�����ơ�

Ĭ��ֵ��`[]`

## ��ݼ�

**`F3 + E`**��������ʾ��ǰ���ע�ӵ�ʵ����Ϣ��HUD��  

**`F3 + M`**��������ʾ�ͻ��������Ϣ��HUD����ͶӰ��Ĭ�����ó�ͻ�������ֶ��޸ġ�

**`F3 + S`**��������ʾ����������Ϣ��HUD��

## ��Ⱦ��

**ʵ����ϢHUD**�����ݲο����ġ�ʵ�����������ȡ�Է������Ϸ��ĩβ,�ͻ��������Ϣȡ�Կͻ���tickĩ��

![](https://raw.githubusercontent.com/wiki/lovexyn0827/MessMod/media/HUD-demo.png)

**ʵ�屬ը����**����ʾ��ը������ʵ����ײ����һЩѡ�еĵ������ھ���ʵ���ڱ�ըӰ��ĳ̶ȵ��߶Ρ������б��赲�Ĳ�����ʾΪ��ɫ������Ϊ��ɫ��   

![](https://raw.githubusercontent.com/wiki/lovexyn0827/MessMod/media/explosion-ray-demo.png)

**�����ʵ����ײ��**����Ҫ��������F3 + B�ṩ����ײ�䣬��Ϊ�����Ǿ��ͻ���Ϊʹ�˶���ƽ����������ģ����˶�ʱ���ܻᡰ�����ϡ�����ˡ�ʵ�����ײ��ᱻ��ȾΪ��ɫ��

![](https://raw.githubusercontent.com/wiki/lovexyn0827/MessMod/media/server-synced-box-demo-0.png)

![](https://raw.githubusercontent.com/wiki/lovexyn0827/MessMod/media/server-synced-box-demo-1.png)

**���巽������**����ʾ��Ҷ�׼������ĵȼ����߶ȡ���Χ������������

![](https://raw.githubusercontent.com/wiki/lovexyn0827/MessMod/media/fluid-info-demo.png)

**����߽���**����ʾ��Ҷ�׼�ķ������ײ���OUTLINE Shape��

![](https://raw.githubusercontent.com/wiki/lovexyn0827/MessMod/media/block-box-demo.png)

**��ʯװ���ź�����ȼ�**����ʾ��Ҷ�׼���м����ͱȽ���������ȼ���

![](https://raw.githubusercontent.com/wiki/lovexyn0827/MessMod/media/redstone-gate-demo.png)

## ������Ʒ��Scarpetʵ�֣�

**��ש**��ʹ��/tick freeze��ͣ�������Ϸ�̡� 
**��ͷ**���ڷ������Ϸ�̱�/tick freeze��ͣʱ����ִ��һ��ʱ�䣬ִ�еĿ������ڸ����ͷ�ĸ����� 
**�½�Ͻ�**��ֱ���Ƴ����з����ʵ�塣  

## HUD��Ϣ��

���а���ʵ��ID�����ڸ�ʵ����󴴽�ǰ������Entityʵ�������������ƣ�`/summon`��ʹ�õ�ʵ�����ͻ��Զ������ƣ������䣨ʵ�����һ�α����غ���ڵĿ������� 
`Pos`��ʵ������ꡣ 
`Motion`��Entity���������`motion`��MCP����`velocity`��Yarn����`deltaMovement`���ٷ�����ֵ,һ��ӽ������ʵ����ٶȡ� 
`Delta`��ʵ����һ�̵�λ�ƣ�����Ϊ�ٶȡ� 
`Yaw,Pitch`��ʵ���ˮƽ����Ǻ����ǡ� 
`Fall Distance`��ʵ������һ����½�����������ƶ��ĳ��ȣ���Ҳ�����ܵ�һЩ�������ص�Ӱ�죬�������ҽ��� 
`General State`������ʵ�干�е�һЩ���������漴�٣����ԣ�������ġ� 
`Health`��LivingEntity�������ҺͿ��׼ܣ�������ֵ�� 
`Forward,Sideways,Upward`����ʵ���˶�AI�йء� 
`Speed`����ʵ���˶��ٶȣ��ֱ���·�ϺͿ��У�����ء� 
`Living State`��`LivingEntity`���е�һЩ���������漴�٣����ԣ�������ġ� 
`Fuse`��TNT�����߳��ȣ���λΪ�̡��˹���ȼ��TNT��ֵ��Ϊ80�� 
`Power`������ļ��ٶȡ�ע�⣬������0.05gt^-1�����������Բ���һֱ���١� 
`Velocity Decay`����������ϵ�����ڲ�ͬ�����ϲ�ͬ��  

## ��д��״̬��state��

### ͨ��

`Gl` :ӵ�и�״̬��ʵ����һ������������� 
`Inv` :ӵ�и�״̬��ʵ�����ߴ󲿷��˺��� 
`Col` :ӵ�и�״̬��ʵ���������ʵ�巢����ײ�� 
`NG` :ӵ�и�״̬��ʵ�岻������Ӱ�졣 
`HC` :��ʾʵ������һ���ƶ��з�����ˮƽ�������ײ�� 
`VC` :��ʾʵ������һ���ƶ��з�������ֱ�������ײ�� 
`Wet` :��ʾʵ���һ���ִ���ˮ�С�
`Sp` :��ʾʵ���ڼ��ܡ� 
`Sn` :��ʾʵ����Ǳ�С� 
`De` :��ʾʵ�����¶ף�������Ǳ��)�� 
`Sw` :��ʾʵ������Ӿ�� 
`Og` :��ʾʵ���Ѿ��ŵء�

### Living

`Hurt` :��ʾʵ������һ�����ܵ����˺��� 
`Fly` :��ʾʵ����ʹ���ʳ���С� 
`Slp` :��ʾʵ����˯�� 
`Dead` :��ʾʵ�������ֵ��0����ͣ�����˵�Ѿ�������

## ��������

1.�ṹ�������Ⱦ���뱻�ؿ������ں�Զ����������
2.��δ��װ��̺��ʱִ����������δ֪����ʱ�����Stacktrace���ڰ�װ��̺�˺�Ҳ����ͨ������`superSecretSetting`����������һ�㡣

## ע������

1.��Mod���ڿ����У�һЩ���ܿ��ܲ����û����bug�����������������ҡ�  
2.�ͻ��������`CilentPlayerEntity`�����Ƕ�Ӧ��`ServerPlayerEntity`����Ϊ����˶��Ĵ󲿷������ڿͻ��˽��С� �Բ鿴����������Ϣ��֧�ֿ����ں����汾�м��롣
3.�ð汾��ר�÷�������֧�ֺ��������ڴ����ȶ������⣬һ���������ֻ�ڵ�����Ϸ������������������ڿͻ��ˣ���ʹ�ø�Mod��  
4.һЩ�����/explode \~ \~ \~ 2147483647 true������ɷ���˿�������ע�⡣