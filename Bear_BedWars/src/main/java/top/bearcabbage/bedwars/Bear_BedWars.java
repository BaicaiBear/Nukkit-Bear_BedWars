package top.bearcabbage.bedwars;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
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
public class Bear_BedWars extends PluginBase implements Listener {

    public Vector3 team1;
    public Vector3 team2;
    public Vector3 team3;
    public Vector3 team4;

    public final Level GETBED = this.getServer().getLevelByName("bedwars");
    public final Location TEAMONE = new Location(0,0,0,GETBED);
    public final Location TEAMTWO = new Location(0,0,1, GETBED);
    public final Location TEAMTHREE = new Location(0,0,2,GETBED);
    public final Location TEAMFOUR = new Location(0,0,3,GETBED);

    public Player ingame[50];
    public int gamenum;

    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int sumnum;

    public void onEnable()
    {
        saveResource("Config.yml");
        team1 = this.getConfig().get("1队进入木牌位置",new Vector3(0,5,0));
        team2 = this.getConfig().get("2队进入木牌位置",new Vector3(0,5,1));
        team3 = this.getConfig().get("3队进入木牌位置",new Vector3(0,5,2));
        team4 = this.getConfig().get("4队进入木牌位置",new Vector3(0,5,3));
        this.getConfig().save();
        num1 = num2 = num3 = num4 = 0;
        ingame = null;
        gamenum = 0;
        this.getLogger().info("[起床战争]插件已加载，作者小熊白菜，邮箱xiaoxiongbaicai@outlook.com");
    }

    @EventHandler
    public boolean onSignChange (SignChangeEvent event) {
        if (event.getLine(0).equals("GetBed")) {
            if (!event.getPlayer().isOp()) {
                event.getPlayer().sendMessage("§4[起床战争]你没有权限使用此功能");
                return false;
            }
            if (!(event.getLine(1).equals("1") || event.getLine(1).equals("2") || event.getLine(1).equals("3") || event.getLine(1).equals("4"))) {
                event.getPlayer().sendMessage("§4[起床战争]你输入的格式有误，请在第一行输入 GetBed 在第二行输入代表队名数字");
                event.setLine(1, "§4[起床战争]你的输入有误，请根据私信提示重新书写");
                return false;
            }
            /*
            if(event.getLine(2).length()==0)

            {
                event.getPlayer().sendMessage("§4[起床战争]你输入的格式有误，请在第一行输入 GetBed 在第二行输入代表队名数字 在第三行输入这个木牌的名字（推荐英文，中文有一定可能性乱码）");
                event.setLine(2,"§4[起床战争]你的输入有误，请根据私信提示重新书写");
                return false;
            }
            */
            if (event.getLine(3).length() != 0 || event.getLine(2).length() != 0) {
                event.getPlayer().sendMessage("§4[起床战争]你输入的格式有误，请在第一行输入 GetBed 在第二行输入代表队名数字");
                event.setLine(3, "§4[起床战争]你的输入有误，请根据私信提示重新书写");
                return false;
            }

            private int team;
            private Location tmps = event.getBlock().getLocation();
            private Vector3 qwq = new Vector3(tmps.x, tmps.y, tmps.z);
            team = event.getLine(1)[0] - '0';
            switch (team) {
                case 1:
                    team1 = qwq;
                    this.getConfig().set("1队进入木牌位置", team1);
                    this.getConfig().save();
                    break;
                case 2:
                    team2 = qwq;
                    this.getConfig().set("2队进入木牌位置", team2);
                    this.getConfig().save();
                    break;
                case 3:
                    team3 = qwq;
                    this.getConfig().set("3队进入木牌位置", team3);
                    this.getConfig().save();
                    break;
                case 4:
                    team4 = qwq;
                    this.getConfig().set("4队进入木牌位置", team4);
                    this.getConfig().save();
                    break;
                default:
                    this.getLogger().warning("[起床战争]出现了没有检出的未知错误，错误原因：设置木牌时文字格式有误");
                    return false;
            }
            return true;
        }
        return false;
    }
    @EventHandler
    public boolean onTouch (PlayerInteractEvent event)
    {

        if(!event.getPlayer().isPlayer())
            return false;
        switch (event.getTouchVector())
        {
            case team1:
                ingame[gamenum] = event.getPlayer();
                gamenum++;
                event.getPlayer().teleport(TEAMONE);
                num1++;
                break;
            case team2:
                ingame[gamenum] = event.getPlayer();
                gamenum++;
                event.getPlayer().teleport(TEAMTWO);
                num2++;
                break;
            case team3:
                ingame[gamenum] = event.getPlayer();
                gamenum++;
                event.getPlayer().teleport(TEAMTHREE);
                num3++;
                break;
            case team4:
                ingame[gamenum] = event.getPlayer();
                gamenum++;
                event.getPlayer().teleport(TEAMFOUR);
                num4++;
                break;
            default:
                event.getPlayer().sendMessage("§4[起床战争]木牌设置有误，请重新设置");
                return false;
        }
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
                onGame(ingame[]);
                for(int i=0;i<gamenum;i++)
                    ingame[i] = null;
                gamenum = 0;
            }
            catch(Exception e)
            {
                this.getLogger().warning("[起床战争]出现未知错误，游戏已停止");
                for(int i=0;i<gamenum;i++)
                    ingame[i] = null;
                gamenum = 0;
                return false;
            }
        }
        return true;
    }

}
