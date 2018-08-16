package top.bearcabbage.bedwars;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.EventHandler;



/*
This Class Created by Bear Cabbage on 2018/08/15.
这个类由小熊白菜与2018/08/15创建。
______                   _____       _     _
| ___ \                 /  __ \     | |   | |
| |_/ / ___  __ _ _ __  | /  \/ __ _| |__ | |__   __ _  __ _  ___
| ___ \/ _ \/ _` | '__| | |    / _` | '_ \| '_ \ / _` |/ _` |/ _ \
| |_/ /  __/ (_| | |    | \__/\ (_| | |_) | |_) | (_| | (_| |  __/
\____/ \___|\__,_|_|     \____/\__,_|_.__/|_.__/ \__,_|\__, |\___|
                                                        __/ |
                                                       |___/
*/
public class BedWars extends PluginBase implements Listener {

    public int team1;
    public int team2;
    public int team3;
    public int team4;

    public Player[] gameplay1 = new Player[10];
    public Player[] gameplay2 = new Player[10];
    public Player[] gameplay3 = new Player[10];
    public Player[] gameplay4 = new Player[10];
    public Player[] gameplay = new Player[50];

    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int sumnum;

    /*
   This function will be created in the futrue version.
   此功能将在未来版本中开发。
   */
    private void CopyLevel() {
        this.getLogger().warning("[起床战争]地图不存在，请手动复制");
    }

    public void onEnable()
    {
        team1 = team2 = team3 = team4 = 0;
        saveResource("Config.yml");
        team1 = this.getConfig().getInt("1队进入木牌位置");
        team2 = this.getConfig().getInt("2队进入木牌位置");
        team3 = this.getConfig().getInt("3队进入木牌位置");
        team4 = this.getConfig().getInt("4队进入木牌位置");
        this.getConfig().save();
        num1 = num2 = num3 = num4 = 0;
        sumnum = 0;
        for(int i=0;i<50;i++)
            gameplay[i] = gameplay1[i] = gameplay2[i] = gameplay3[i] = gameplay4[i] = null;
        if(this.getServer().getLevelByName("bedwars")==null)
            CopyLevel();
        this.getLogger().info("[起床战争]插件已加载，作者小熊白菜，邮箱xiaoxiongbaicai@outlook.com");
    }


    /*
    Set the join sign.
    设置进入游戏的木牌。
     */
    @EventHandler
    public boolean onSignChange (SignChangeEvent event) {
        if (event.getLine(0).equals("BedWars")) {
            if (!event.getPlayer().isOp()) {
                event.getPlayer().sendMessage("§4[起床战争]你没有权限使用此功能");
                return false;
            }
            if (!(event.getLine(1).equals("1") || event.getLine(1).equals("2") || event.getLine(1).equals("3") || event.getLine(1).equals("4"))) {
                event.getPlayer().sendMessage("§4[起床战争]你输入的格式有误，请在第一行输入 BedWars 在第二行输入代表队名数字");
                event.setLine(1, "§4[起床战争]你的输入有误，请根据私信提示重新书写");
                return false;
            }
            /*
            if(event.getLine(2).length()==0)

            {
                event.getPlayer().sendMessage("§4[起床战争]你输入的格式有误，请在第一行输入 BedWars 在第二行输入代表队名数字 在第三行输入这个木牌的名字（推荐英文，中文有一定可能性乱码）");
                event.setLine(2,"§4[起床战争]你的输入有误，请根据私信提示重新书写");
                return false;
            }
            */
            if (event.getLine(3).length() != 0 || event.getLine(2).length() != 0) {
                event.getPlayer().sendMessage("§4[起床战争]你输入的格式有误，请在第一行输入 BedWars 在第二行输入代表队名数字");
                event.setLine(3, "§4[起床战争]你的输入有误，请根据私信提示重新书写");
                return false;
            }

            int team;
            int id = event.getBlock().getId();
            team = event.getLine(1).charAt(0) - '0';
            switch (team) {
                case 1:
                    team1 = id;
                    this.getConfig().set("1队进入木牌位置", team1);
                    this.getConfig().save();
                    break;
                case 2:
                    team2 = id;
                    this.getConfig().set("2队进入木牌位置", team2);
                    this.getConfig().save();
                    break;
                case 3:
                    team3 = id;
                    this.getConfig().set("3队进入木牌位置", team3);
                    this.getConfig().save();
                    break;
                case 4:
                    team4 = id;
                    this.getConfig().set("4队进入木牌位置", team4);
                    this.getConfig().save();
                    break;
                default:
                    this.getLogger().warning("[起床战争]出现了没有检出的未知错误，错误原因：设置木牌时文字格式有误");
                    return false;
            }
            return true;
        }
        event.getPlayer().sendMessage("[起床战争]设置成功");
        return false;
    }
    @EventHandler
    public boolean onTouch (PlayerInteractEvent event)
    {
        int tmpid = event.getBlock().getId();

        if(tmpid!=this.team1&&tmpid!=this.team2&&tmpid!=this.team3&&tmpid!=this.team4)
            return false;

        final Level BEDWARS = this.getServer().getLevelByName("bedwars");
        final Location TEAMONE = new Location(227,4+2,442,BEDWARS);
        final Location TEAMTWO = new Location(256,9+2,358,BEDWARS);
        final Location TEAMTHREE = new Location(331,9+2,441,BEDWARS);
        final Location TEAMFOUR = new Location(279,9+2,513,BEDWARS);

        if(!event.getPlayer().isPlayer())
            return false;

        if(this.team1==tmpid) {
            event.getPlayer().setGamemode(2);
            event.getPlayer().teleport(TEAMONE);
            gameplay1[gameplay1.length] = event.getPlayer();
            num1++;
        }
        if(this.team2==tmpid) {
            event.getPlayer().setGamemode(2);
            event.getPlayer().teleport(TEAMTWO);
            gameplay2[gameplay2.length] = event.getPlayer();
            num2++;
        };
        if(this.team3==tmpid) {
            event.getPlayer().setGamemode(2);
            event.getPlayer().teleport(TEAMTHREE);
            gameplay3[gameplay3.length] = event.getPlayer();
            num3++;
        }
        if(this.team4==tmpid) {
            event.getPlayer().setGamemode(2);
            event.getPlayer().teleport(TEAMFOUR);
            gameplay4[gameplay4.length] = event.getPlayer();
            num4++;
        }
        gameplay[sumnum] = event.getPlayer();
        event.getPlayer().sendMessage("§a[起床战争]你以进入游戏地图，请等待游戏开始");
        sumnum = num1 + num2 + num3 + num4;
        int f;
        f = 0;
        if(num1!=0)f++;
        if(num2!=0)f++;
        if(num3!=0)f++;
        if(num4!=0)f++;
        if(f>1 && sumnum>3)
        {
            try{
                f = 0;
                this.getLogger().info("[起床战争]即将进入游戏，请做好准备");
                for(int i=0;i<1000000;i++)
                    for(int j=0;j<1000;j++);
                BedGame.onGame(sumnum,gameplay1,gameplay2,gameplay3,gameplay4,gameplay);
            }
            catch(Exception e)
            {
                this.getLogger().warning("[起床战争]出现未知错误，游戏已停止");
                return false;
            }
            finally {
                sumnum = num1 = num2 = num3 = num4 =0;
            }
        }
        return true;
    }



}
