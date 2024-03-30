package ttv.migami.mdf.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import ttv.migami.mdf.common.network.CreeperFruitHandler;
import ttv.migami.mdf.common.network.FireworkFruitHandler;

public class S2SCreeperFruit extends PlayMessage<S2SCreeperFruit>
{
    private int move;

    public S2SCreeperFruit() {}

    public S2SCreeperFruit(int move)
    {
        this.move = move;
    }

    @Override
    public void encode(S2SCreeperFruit message, FriendlyByteBuf buffer)
    {
        buffer.writeInt(message.move);
    }

    @Override
    public S2SCreeperFruit decode(FriendlyByteBuf buffer)
    {
        return new S2SCreeperFruit(buffer.readInt());
    }

    @Override
    public void handle(S2SCreeperFruit message, MessageContext context)
    {
        context.execute(() ->
        {
            ServerPlayer player = context.getPlayer();
            if(player != null && !player.isSpectator())
            {
                CreeperFruitHandler.moveHandler(player, message.move);
            }
        });
        context.setHandled(true);
    }
}
