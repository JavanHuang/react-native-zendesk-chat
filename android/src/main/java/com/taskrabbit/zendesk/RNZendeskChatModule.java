package com.taskrabbit.zendesk;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.prechat.PreChatForm;
import com.zopim.android.sdk.model.VisitorInfo;
import com.zopim.android.sdk.prechat.ZopimChatActivity;

import java.lang.String;

public class RNZendeskChatModule extends ReactContextBaseJavaModule {
    private ReactContext mReactContext;

    public RNZendeskChatModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNZendeskChatModule";
    }

    @ReactMethod
    public void setVisitorInfo(ReadableMap options) {
        VisitorInfo.Builder builder = new VisitorInfo.Builder();

        if (options.hasKey("name")) {
            builder.name(options.getString("name"));
        }
        if (options.hasKey("email")) {
            builder.email(options.getString("email"));
        }
        if (options.hasKey("phone")) {
            builder.phoneNumber(options.getString("phone"));
        }

        VisitorInfo visitorData = builder.build();

        ZopimChat.setVisitorInfo(visitorData);
    }

    @ReactMethod
    public void init(String key) {
        PreChatForm defaultPreChat = new PreChatForm.Builder()
                .name(PreChatForm.Field.REQUIRED)
                .email(PreChatForm.Field.REQUIRED)
                .phoneNumber(PreChatForm.Field.REQUIRED)
                .department(PreChatForm.Field.REQUIRED_EDITABLE)
                .message(PreChatForm.Field.REQUIRED)
                .build();

        ZopimChat.init(key)
                 .preChatForm(defaultPreChat)
                 .build();
    }

    @ReactMethod
    public void startChat(ReadableMap options) {
        setVisitorInfo(options);

//        PreChatForm.Builder preChatFormBuilder = new PreChatForm.Builder();
//
//        preChatFormBuilder.name(PreChatForm.Field.REQUIRED);
//        if (options.hasKey("emailNotRequired")) {
//            preChatFormBuilder.email(PreChatForm.Field.OPTIONAL);
//        } else {
//            preChatFormBuilder.email(PreChatForm.Field.REQUIRED);
//        }
//
//        if (options.hasKey("phoneNotRequired")) {
//            preChatFormBuilder.phoneNumber(PreChatForm.Field.OPTIONAL);
//        } else {
//            preChatFormBuilder.phoneNumber(PreChatForm.Field.REQUIRED);
//        }
//
//        if (options.hasKey("departmentNotRequired")) {
//            preChatFormBuilder.department(PreChatForm.Field.OPTIONAL);
//        } else {
//            preChatFormBuilder.department(PreChatForm.Field.REQUIRED_EDITABLE);
//        }
//
//        if (options.hasKey("messageNotRequired")) {
//            preChatFormBuilder.message(PreChatForm.Field.OPTIONAL);
//        } else {
//            preChatFormBuilder.message(PreChatForm.Field.REQUIRED);
//        }
//
//        // build pre chat form config
//        PreChatForm preChatForm = preChatFormBuilder.build();
//
//        // build session config
//        ZopimChat.SessionConfig config = new ZopimChat.SessionConfig()
//                .preChatForm(preChatForm);

        // start chat activity with config
        Activity activity = getCurrentActivity();
        if (activity != null) {
            activity.startActivity(new Intent(mReactContext, ZopimChatActivity.class));
        }

    }
}
