package top.bearcabbage.bedwars;


import cn.nukkit.Player;
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
public class BedGame extends BedWars{

    public static void onGame(int sumnum, cn.nukkit.Player[] gamer1, cn.nukkit.Player[] gamer2, cn.nukkit.Player[] gamer3, cn.nukkit.Player[] gamer4, cn.nukkit.Player[] gamer) {
        for(int j=10;j>0;j--){
            for(int i=0;i<sumnum;i++)
                gamer[i].sendMessage("§b[起床战争]倒计时"+" §4"+j+"s ");
            try
            {
                Thread.currentThread().sleep(1000);
            }
            catch(Exception e){
                this.getLogger().warning("[起床战争]出现未知错误");
            }
        }
        for(int i=0;i<sumnum;i++)
            gamer[i].sendMessage("§c[起床战争] GO！");
        for(int i=0;i<sumnum;i++)
            gamer[i].setGamemode(0);

        @EventHandler
        public void onGold(PlayerMoveEvent event){
            ;
        }


    }

}
