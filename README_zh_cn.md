# Just A Mess��һ���㣩
`һ�������ṩ������������������һЩ��ǿ���ܵ�Minecraft Mod`  

��������������˵�����Mod�����˺ܶ෽��Ĺ��ܣ�������ƣ���Ϣ��ʾ�����Ը����Լ�һЩBug�޸��ȣ�����Щ�������д�ģ��ǳ��ң�����������Ҳ���ҡ�

�������ԣ� 
[English](/README.md)  

## ����
1.Fabric Loader 0.7.4+  
2.gnembon�ĵ�̺Mod��fabric-carpet�������ֹ�����Ҫ������ǿ���Ƽ��������Ǳ��裩  
3.Minecraft 1.16.4��1.16.5  
4.Minecraft 1.16.x ��Ҫ�����ж���  

## ����
���������ü�������ס����������Ϊ��ѡ���֡�
`/entityconfig <targets> enableStepHeight|disableStepHeight`��������ֹtargetsָ����ʵ��ֱ�����Ϸ��顣  
`/entityconfig localPlayer enableStepHeight|disableStepHeight`��������ֹ�ͻ������ֱ�����Ϸ��顣  
`/entityfield <target> get <fieldName>`����ȡ����targetָ����ʵ��Ķ�������ΪfieldName�����������ע�⣬�˴���������������field_827��intermediary names�����Զ���ȡ����ӳ�䱻֧��ǰ��Ҫ�˹������Ա���⡣  
`/entityfield <target> listAvailableFields`����ȡ����targetָ����ʵ��Ķ���������������������б�  
`/entityfield <target> set <fieldName> <newValue>`��������targetָ����ʵ��Ķ�������ΪfieldName���������ֵ��ΪnewValue��֧�ֵ����ͣ�int��float��double��boolean�����ڿ�������ʧ�ܣ���Vec3d��  
`/explode <pos> <power> [<fire>]`����pos������һ������Ϊpower�ı�ը������ָ��fireΪtrueʹ��ը���ɻ��档��ը�������������ⵥ���ȸ��������������������NaN  
`/messcfg`����ȡ��ǰMod�汾��   
`/messcfg blockShapeToBeRendered  COLLIDER|OUTLINE|RAYSAST|SIDES|VISUAL`��������ѡ��renderBlockShapeΪtrueʱ����Ⱦ�ķ����������͡�COLLIDER��Ӧ�������ײ�䣬OUTLINE��Ӧ��������Ҷ�׼ʱ��ʾ�ı߽硣
`/messcfg enabledTools <bool>`�����û���ù�����Ʒ�������÷������ġ�  
`/messcfg entityExplosionInfluence <bool>`�������Ƿ��ڱ�ըӰ�쵽ʵ��ʱ�����������ʵ�屻Ӱ���������ڵ�ǰ�汾�д���һ��bug��ʹ����Ϣ�ڷǿ��������²��ᱻ���������ֻ��������   
`/messcfg entityExplosionRaysVisiblity <bool>`�����û����ʵ�屬ը������Ⱦ�� 
`/messcfg entityExplosionRaysVisiblity setLifetime <ticks>`������ʵ�屬ը���ߵ���ʾʱ������λΪ�̡�
`/messcfg mobFastKill <bool>`�����������ﱻ/kill ...ɱ��ʱ�Ƿ�ֱ���Ƴ����ǳ����˺���
`/messcfg reloadConfig`����mcwmem.prop��ȡ���µ�������Ϣ��
`/messcfg renderBlockShape <bool>`�����û���÷���������Ⱦ��
`/messcfg renderFluidShape <bool>`�����û���������������߶Ⱥ�������������Ⱦ��
`/messcfg serverSyncedBox <bool>`�����û���÷������ײ����ʾ��  
`/messcfg setHudDisplay bottomLeft|bottomRight|topLeft|TopRight`������ʵ����ϢHUD��λ�á�
`/messcfg debugStickSkipsInvaildState <bool>`����ֹ���԰�������������Ƿ�״̬��Ŀǰ���ѡ����һЩ����²�������������������������shape����ʱ�Կ��Խ���������Ƿ�״̬�� 
`/messcfg tntChunkLoading <bool>`��������ֹTNTʵ������������м������飬������һЩTNT��ʱ���ܻ����������� 
`/messcfg projectileChunkLoading <bool>`��������ֹ������ʵ������������м������飬������������ʱ���ܻ����������� 
`/messcfg maxClientTicksPerFrame <ticks>`������FPS����20ʱÿ֡�ͻ��˿������м�����Ϸ�̣�������Ϊһ����Сֵ�����޸���FPSʱ�޷��л�����״̬��Bug�� 
`/modify <targets> <key> <val>`���޸�targetsָ����ʵ������ԣ��ǲ���/entityfield�ļ���÷���  
`/modify <target> remove`���Ƴ�targetsָ����ʵ�塣  
`/moventity <targets> <delta> entity self|piston|shulkerBox|player|shulker`��ʹ��Entity.move()�ƶ�targetsָ����ʵ�壬������deltaָ����ͨ��ѡ��self����Ϊ���һ��������ִ����ɺ����ʵ��λ�ơ�  
`/moventity <targets> <delta> projectile`��ʹ�õ������ƶ��ķ�ʽ�ƶ�targetָ����ʵ�壬������deltaָ����Ŀǰ�ƺ�����һЩbug��  
`/poi get <pos>`��ȡpos����POI����Ȥ�㣩�� 
`/poi getDistanceToNearestOccupied <pos>`����������Ĵ�����վ����ĳ�ֳ߶��µľ��루������������Ϊ��λ�������پ��룩��
`/poi scan <center> <radius> <type>`��Ѱ����centerΪ���ģ��뾶Ϊradius����������Ϊtype��POI����������ڷ������ꡣ
`/poi scanCobic <corner1> <corner2> <type>`��Ѱ�Ҹ�������������䳤��������������Ϊtype��POI��
`/poi set <pos> <type> <replace>`����pos����Ϊtypeָ����POI��������POI��replaceΪfalse�򲻻����á� 
`/raycast blocks <from> <to> [visual]`������from��to�������߶��Ƿ񱻷����赲����ʽ�����ڵ��������ײ���ͱ�ը�Ӵ��ʼ����е�raycast��ִ�к�������������з������꣨�����ظ���������������赲������赲�߶εķ�������꼰���赲�߶ε����Լ���������꣬�������Missed�����������visual�������̻ᱻ���ӻ��������ܼ��ķ���������ʾΪǳ��ɫ���赲�߶εķ�����ײ��ᱻ��ʾ��ɫ����������ᱻ��ʾΪ��ɫ���߶εı��赲ǰ��Ĳ��ֱַ�Ϊ��ɫ�ͺ�ɫ��
`/raycast entities <from> <to> <expand> <excludeSender> [visual]`�����from��to�������߶��Ƿ񱻷����赲����ʽ�����ڵ��������ײ��顣ִ�к�����赲�߶ε�ʵ�����ͣ����Զ������ƣ�����ID����ײ�����꣬�������Missed��expand����Ӱ���ȡʵ��ķ�Χ��ģ�ⵯ�������ײ���ʱ����ָ��Ϊ��������ײ���ȵ�һ�����1��
`/repeat <times> <feedbacks> ...`���ظ�ִ��ĳ������times�Σ�������feedback��ָ���Ƿ��������������
`/rng world setSeed <seed>`�����õ�ǰά�ȵ�RNG�������������������Ϊseed��  
`/rng world next int <bounds>`��ȡ��ǰά�ȵ���������������ɵ���һ��������[0,bounds)�ڵ�������
`/rng world next int|float|double|boolean`��ȡ��ǰά�ȵ���������������ɵ���һ��������� 
`/rng <target> ...`����ǰ�����������ƣ�ֻ��RNG����targetָ����ʵ����Ǹ�ά�ȡ�  
`/setexplosionblock <blockState> [<fireState>]`��ʹ��ը���ƻ�����ʱ����blockStateָ���ķ�������ǿ�����������ը��Ҫ���õĻ�ȫ���滻��fireStateָ���ķ��顣  
`/tileentity get <pos>`��ȡpos���ķ���ʵ����Ϣ��  
`/tileentity set <pos> <type> [<tag>]`������pos����Ϊtypeָ���ķ���ʵ�塣~����ָ��tag��Ϊ����ʵ��ĳ�ʼ����~����ʱ����pos���ķ��鲻֧�ָ÷���ʵ�壬���û�ʧ�ܣ���  
`/tileentity remove <pos>`���Ƴ�pos���ķ���ʵ�塣��Ŀǰ�汾�У�����ô�����һ����Ҫ����ʵ��ķ��飬���Ƴ���ô�����ʵ��ᱻ�÷����������ã�һ��bug����  

## ��ݼ�
`F3+E`��������ʾ��ǰ���ע�ӵ�ʵ����Ϣ��HUD��  
`F3+M`��������ʾ�ͻ��������Ϣ��HUD����ͶӰ��Ĭ�����ó�ͻ�������ֶ��޸ġ�

## ��Ⱦ��
`ʵ����ϢHUD`�����ݲο����ġ�ʵ�����������ȡ�Է������Ϸ��ĩβ,�ͻ��������Ϣȡ�Կͻ���tickĩ��
`ʵ�屬ը����`����ʾ��ը������ʵ����ײ����һЩѡ�еĵ������ھ���ʵ���ڱ�ըӰ��ĳ̶ȵ��߶Ρ������б��赲�Ĳ�����ʾΪ��ɫ������Ϊ��ɫ��   
`�������ײ��`����Ҫ��������F3+B�ṩ����ײ�䣬��Ϊ�����Ǿ��ͻ���Ϊʹ�˶���ƽ����������ģ����˶�ʱ���ܻᡰ�����ϡ�����ˡ�ʵ�����ײ��ᱻ��ȾΪ��ɫ��

## ������Ʒ��Scarpetʵ�֣�
`��ש`��ʹ��/tick freeze��ͣ�������Ϸ�̡�  
`��ͷ`���ڷ������Ϸ�̱�/tick freeze��ͣʱ����ִ��һ��ʱ�䣬ִ�еĿ������ڸ����ͷ�ĸ�����  
`�½�Ͻ�`��ֱ���Ƴ����з����ʵ�塣  

## HUD��Ϣ��
`����`������ʵ��ID�����ڸ�ʵ����󴴽�ǰ������Entityʵ�������������ƣ�/summon��ʹ�õ�ʵ�����ͻ��Զ������ƣ������䣨ʵ�����һ�α����غ���ڵĿ�������  
`Pos`��ʵ������ꡣ  
`Motion`��Entity���������motion��MCP����velocity��Yarn����deltaMovement���ٷ�����ֵ,һ��ӽ������ʵ����ٶȡ�  
`Delta`��ʵ����һ�̵�λ�ƣ�����Ϊ�ٶȡ�  
`Yaw,Pitch`��ʵ���ˮƽ����Ǻ����ǡ�  
`Fall Distance`��ʵ������һ����½�����������ƶ��ĳ��ȣ���Ҳ�����ܵ�һЩ�������ص�Ӱ�죬�������ҽ���  
`General State`������ʵ�干�е�һЩ���������漴�٣����ԣ�������ġ�  
`Health`��LivingEntity�������ҺͿ��׼ܣ�������ֵ��  
`Forward,Sideways,Upward`����ʵ���˶�AI�йء�  
`Speed`����ʵ���˶��ٶȣ��ֱ���·�ϺͿ��У�����ء�  
`Living State`��LivingEntity���е�һЩ���������漴�٣����ԣ�������ġ�  
`Fuse`��TNT�����߳��ȣ���λΪ�̡��˹���ȼ��TNT��ֵ��Ϊ80��  
`Power`������ļ��ٶȡ�ע�⣬������0.05gt^-1�����������Բ���һֱ���١�  
`Velocity Decay`����������ϵ�����ڲ�ͬ�����ϲ�ͬ��  
## ��д��״̬��state��
### General
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
2.��δ��װ��̺��ʱִ����������δ֪����ʱ�����Stacktrace���ڰ�װ��̺�˺�Ҳ����ͨ������superSecretSetting����������һ�㡣

## ע������

1.��Mod���ڿ����У�һЩ���ܿ��ܲ����û����bug�����������������ҡ�  
2.�ͻ��������CilentPlayerEntity�����Ƕ�Ӧ��ServerPlayerEntity����Ϊ����˶��Ĵ󲿷������ڿͻ��˽��С� 
3.�ڸð汾�У�ר�÷���������֧�֣���ֻ�ڵ�����Ϸ������������������ڿͻ��ˣ���ʹ�ø�Mod��  
4.һЩ�����/explode \~ \~ \~ 2147483647 true������ɷ���˿�������ע�⡣