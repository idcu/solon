package org.noear.solon.view.enjoy;

import com.jfinal.template.Directive;
import org.noear.solon.auth.AuthUtil;
import org.noear.solon.auth.tags.AuthConstants;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.LifecycleIndex;
import org.noear.solon.core.Plugin;
import org.noear.solon.core.handle.RenderManager;
import org.noear.solon.core.util.ClassUtil;
import org.noear.solon.view.enjoy.tags.AuthPermissionsTag;
import org.noear.solon.view.enjoy.tags.AuthRolesTag;

public class XPluginImp implements Plugin {
    @Override
    public void start(AppContext context) {
        EnjoyRender render = EnjoyRender.global();

        context.lifecycle(LifecycleIndex.PLUGIN_BEAN_USES, () -> {
            context.beanForeach((k, v) -> {
                if (k.startsWith("view:")) { //java view widget
                    if (Directive.class.isAssignableFrom(v.clz())) {
                        render.putDirective(k.split(":")[1], new EnjoyDirectiveFactory(v));
                    }
                    return;
                }

                if (k.startsWith("share:")) { //java share object
                    render.putVariable(k.split(":")[1], v.raw());
                    return;
                }
            });
        });

        RenderManager.register(render);
        RenderManager.mapping(".shtm", render);
        context.wrapAndPut(EnjoyRender.class, render);

        if (ClassUtil.hasClass(() -> AuthUtil.class)) {
            render.putDirective(AuthConstants.TAG_authPermissions, AuthPermissionsTag.class);
            render.putDirective(AuthConstants.TAG_authRoles, AuthRolesTag.class);
        }
    }
}
