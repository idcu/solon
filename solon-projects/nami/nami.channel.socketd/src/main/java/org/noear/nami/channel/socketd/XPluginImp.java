package org.noear.nami.channel.socketd;

import org.noear.nami.NamiManager;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2021/1/3 created
 */
public class XPluginImp implements Plugin {
    @Override
    public void start(AppContext context) {
        NamiManager.reg("tcp", SocketdClientChannel.instance);
        NamiManager.reg("ws", SocketdClientChannel.instance);
        NamiManager.reg("wss", SocketdClientChannel.instance);
    }
}
