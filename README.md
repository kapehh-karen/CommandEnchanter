CommandEnchanter
================

Позволяет чарить предметы за игровые деньги.

<b>Команды:</b>
<ul>
<li><code>/enchanterx [enchant name] [enchant level]</code> - чарит вещь в руке</li>
<li><code>/enchanterx reload</code> - перезагружает конфиг</li>
</ul>

<b>Права:</b>
<ul>
<li><code>commandenchanter.use</code> - права на пользование командой</li>
<li><code>commandenchanter.reload</code> - права на перезагрузку плагина</li>
</ul>

<b>Конфиг:</b>
<pre>commandenchanter:
    evalcost: 'if (lvl > 10) (lvl - 10) * 300 + 1500; else if (lvl > 5) (lvl - 5) * 200 + 500; else lvl * 100;' # строка вычисления стоимости чарования, где lvl переменная с требуемым уровнем чара
    sets: # идет перечисление набор:предметы
        helmet: [ 298, 302, 306, 310, 314 ]
        chestplate: [ 299, 303, 307, 311, 315 ]
        leggings: [ 300, 304, 308, 312, 316 ]
        boots: [ 301, 305, 309, 313, 317 ]
        axe: [ 271, 275, 258, 286, 279 ]
        hoe: [ 290, 291, 292, 294, 293 ]
        shovel: [ 269, 273, 256, 284, 277 ]
        pickaxe: [ 270, 274, 257, 285, 278 ]
    enchants: # идет перечисление доступных чаров
        protection:
            allowsets: [ helmet, chestplate, leggings, boots ] # доступные наборы для чара
            eid: 0 # идентификатор чара
            maxlevel: 12 # максимальный лвл для него доступный для чара
        efficiency:
            allowsets: [ axe, hoe, shovel, pickaxe ]
            eid: 32
            maxlevel: 12</pre>

<b>Пример:</b>
<ul>
<li><code>/enchanterx protection 10</code> - зачарит предмет в руке (если он удовлетворяет условиям в конфиге) на Защита Х</li>
</ul>

<b>Требует:</b> Vault, PluginManager
