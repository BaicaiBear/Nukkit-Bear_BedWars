package top.bearcabbage.bedwars;


import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.event.EventHandler;


/*
This Class Created by Bear Cabbage on 2018/08/16.
这个类由小熊白菜与2018/08/16创建。
______                   _____       _     _
| ___ \                 /  __ \     | |   | |
| |_/ / ___  __ _ _ __  | /  \/ __ _| |__ | |__   __ _  __ _  ___
| ___ \/ _ \/ _` | '__| | |    / _` | '_ \| '_ \ / _` |/ _` |/ _ \
| |_/ /  __/ (_| | |    | \__/\ (_| | |_) | |_) | (_| | (_| |  __/
\____/ \___|\__,_|_|     \____/\__,_|_.__/|_.__/ \__,_|\__, |\___|
                                                        __/ |
                                                       |___/
*/
public class BedGame extends BedWars implements Listener {

    public int oknum=0;

    public boolean onCommand(CommandSender sender, Command cmd, String zhiling, String[] liebiao) {
        Player[] gamer = this.gameplay;
        if (this.include(this.gameplay, sender) != -1 && zhiling.equals("bedwars")) {
            if (liebiao.length != 1) {
                sender.sendMessage("§4[起床战争]你输入的语法不正确");
                return false;
            }
            if (liebiao[0].equals("start")) oknum++;
        }
        if (oknum == new BedWars().gameplay.length) {
            for (int j = 10; j > 0; j--) {
                for (int i = 0; i < oknum; i++)
                    gamer[i].sendMessage("§b[起床战争]倒计时" + " §4" + j + "s ");
                try {
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    this.getLogger().warning("[起床战争]出现未知错误");
                }
            }
            for (int i = 0; i < oknum; i++)
                gamer[i].sendMessage("§c[起床战争] GO！");
            for (int i = 0; i < oknum; i++)
                gamer[i].setGamemode(0);
        }
    }

    public int include(Object[] obj, Object objj) {
        int size = obj.length;
        for(int i=0;i<size;i++) {
            if(obj[i].equals(objj));
                return i;
        }
        return -1;
    }

    @EventHandler
    public void onGold (PlayerMoveEvent event) {
        if(this.include(BedWars.game,event.getPlayer()))
            if(this.include(this.goldblock,event.getPlayer().getLocation())
        {

        };


    }

}
