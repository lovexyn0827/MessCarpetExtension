#Minecraft���������ǿMod
`һ�������ṩ������������������Minecraft Mod`  
�������ԣ� 
[English](/README.md)  
##����
1.Fabric Loader  
2.gnembon�ĵ�̺Mod��fabric-carpet��  
3.Minecraft 1.16.4�������1.16.4��  
4.Minecraft 1.16.4 ��Ҫ�����ж���  
##����
`/entityconfig <targets> enableStepHeight|disableStepHeight`��������ֹtargetsָ����ʵ��ֱ�����Ϸ��顣  
`/entityconfig localPlayer enableStepHeight|disableStepHeight`��������ֹ�ͻ������ֱ�����Ϸ��顣  
`/entityfield <target> get <fieldName>`����ȡ����targetָ����ʵ��Ķ�������ΪfieldName�����������ע�⣬�˴���������������field_827��intermediary names�����Զ���ȡ����ӳ�䱻֧��ǰ��Ҫ�˹������Ա���⡣  
`/entityfield <target> listAvailableFields`����ȡ����targetָ����ʵ��Ķ���������������������б�  
`/entityfield <target> set <fieldName> <newValue>`��������targetָ����ʵ��Ķ�������ΪfieldName���������ֵ��ΪnewValue��֧�ֵ����ͣ�int��float��double��boolean�����ڿ�������ʧ�ܣ���Vec3d��  
`/explode <pos> <power> <fire>`����pos������һ������Ϊpower�ı�ը������ָ��fireΪtrueʹ��ը���ɻ��档  
`/mcwmem`����ȡ��ǰMod�汾��  
`/mcwmem setDisplay <location>`����ʵ����ϢHUD��λ������locationָ����λ�á�  
`/mcwmem reloadConfig`����mcwmem.prop��ȡ���µ�������Ϣ��  
`/mcwmem entityExplosionRaysVisiblity <bool>`�����û����ʵ�屬ը������Ⱦ��  
`/mcwmem entityExplosionRaysVisiblity setLifetime <ticks>`������ʵ�屬ը���ߵ���ʾʱ������λΪ�̡�  
`/mcwmem serverSyncedBox <bool>`�����û���÷������ײ����ʾ��  
`/mcwmem mobFastKill <bool>`�����������ﱻ/kill ...ɱ��ʱ�Ƿ�ֱ���Ƴ����ǳ����˺���  
`/mcwmem enabledTools <bool>`�����û���ù�����Ʒ�������÷������ġ�  
~`/mcwmem entityExplosionInfluence <bool>`~�������Ƿ��ڱ�ըӰ�쵽ʵ��ʱ�����������ʵ�屻Ӱ���������ڵ�ǰ�汾�д���һ��bug��ʹ����Ϣ�ڷǿ��������²��ᱻ���������ֻ��������  
`/modify <targets> <key> <val>`���޸�targetsָ����ʵ������ԣ��ǲ���/entityfield�ļ���÷���  
`/modify <target> remove`���Ƴ�targetsָ����ʵ�塣  
`/moventity <targets> <delta> entity self|piston|shulkerBox|player|shulker`��ʹ��Entity.move()�ƶ�targetsָ����ʵ�壬������deltaָ����ͨ��ѡ��self����Ϊ���һ��������  
`/moventity <targets> <delta> projectile`��ʹ�õ������ƶ��ķ�ʽ�ƶ�targetָ����ʵ�壬������deltaָ����Ŀǰ�ƺ�����һ��bug��  
`/poi get <pos>`��ȡpos����POI����Ȥ�㣩��  
`/poi set <pos> <type> <replace>`����pos����Ϊtypeָ����POI��������POI��replaceΪfalse�򲻻����á�  
`/rng world setSeed <seed>`�����õ�ǰά�ȵ�RNG�������������������Ϊseed��  
`/rng world next int|float|double|boolean`��ȡ��ǰά�ȵ���������������ɵ���һ���������������intʱ����������һ��������ָ���Ͻ硣  
`/rng <target> ...`����ǰ�����������ƣ�ֻ��RNG����targetָ����ʵ����Ǹ�ά�ȡ�  
`/setexplosionblock <blockState> <fireState>`��ʹ��ը���ƻ�����ʱ����blockStateָ���ķ�������ǿ�����������ը��Ҫ���õĻ�ȫ���滻��fireStateָ���ķ��顣  
`/tileentity get <pos>`��ȡpos���ķ���ʵ����Ϣ��  
`/tileentity set <pos> <type> <tag>`������pos����Ϊtypeָ���ķ���ʵ�塣~����ָ��tag��Ϊ����ʵ��ĳ�ʼ����~����ʱ����pos���ķ��鲻֧�ָ÷���ʵ�壬���û�ʧ�ܣ���  
`/tileentity remove <pos>`���Ƴ�pos���ķ���ʵ�塣��Ŀǰ�汾�У�����ô�����һ����Ҫ����ʵ��ķ��飬���Ƴ���ô�����ʵ��ᱻ�÷����������ã�һ��bug����  
##��ݼ�
`F3+E`��������ʾ��ǰ���ע�ӵ�ʵ����Ϣ��HUD��  
`F3+M`��������ʾ�ͻ��������Ϣ��HUD��
##��Ⱦ��
`ʵ����ϢHUD`�����ݲο����ġ�ע��ʵ�����������ȡ�Է������Ϸ��ĩβ,�ͻ��������Ϣȡ�Կͻ���tickĩ��
`ʵ�屬ը����`����ʾ��ը������ʵ����ײ����һЩѡ�еĵ������ھ���ʵ���ڱ�ըӰ��ĳ̶ȵ��߶Ρ����赲������ʾΪ��ɫ������Ϊ��ɫ��   
`�������ײ��`����Ҫ��������F3+B�ṩ����ײ�䣬��Ϊ�����Ǿ��ͻ���Ϊʹ�˶���ƽ����������ģ����˶�ʱ���ܻᡰ�����ϡ�����ˡ�  
##������Ʒ
`��ש`��ʹ��/tick freeze��ͣ�������Ϸ�̡�  
`��ͷ`���ڷ������Ϸ�̱�/tick freeze��ͣʱ����ִ��һ��ʱ�䣬ִ�еĿ������ڸ����ͷ�ĸ�����  
`�½�Ͻ�`���Ƴ����з����ʵ�塣  
##HUD��Ϣ��
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
##��д��״̬��state��
###General
`Gl` :ӵ�и�״̬��ʵ����һ�������������  
`Inv` :ӵ�и�״̬��ʵ�����ߴ󲿷��˺���  
`Col` :ӵ�и�״̬��ʵ���������ʵ�巢����ײ��  
`NG` :ӵ�и�״̬��ʵ�岻������Ӱ�졣  
`HC` :��ʾʵ������һ���ƶ��з�����ˮƽ�������ײ��  
`VC` :��ʾʵ������һ���ƶ��з�������ֱ�������ײ��  
`Wet` :��ʾʵ���һ���ִ���ˮ��.  
`Sp` :��ʾʵ���ڼ��ܡ�  
`Sn` :��ʾʵ����Ǳ�С�  
`De` :��ʾʵ�����¶ף�������Ǳ��)��  
`Sw` :��ʾʵ������Ӿ��  
###Living
`Hurt` :��ʾʵ������һ�����ܵ����˺���  
`Fly` :��ʾʵ����ʹ���ʳ���С�  
`Slp` :��ʾʵ����˯��  
`Dead` :��ʾʵ�������ֵ��0����ͣ�����˵�Ѿ�������  
##����
1.��Mod���ڿ����У�һЩ���ܿ��ܲ����û����bug�����������������ҡ�  
2.�ͻ��������CilentPlayerEntity�����Ƕ�Ӧ��ServerPlayerEntity����Ϊ����˶��Ĵ󲿷������ڿͻ��˽��С�  
3.�ڸð汾�У�ר�÷���������֧�֣���ֻ�ڵ�����Ϸ���������ʹ�ø�Mod��  
4.һЩ�����/explode \~ \~ \~ 2147483647 true������ɷ���˿�������ע�⡣