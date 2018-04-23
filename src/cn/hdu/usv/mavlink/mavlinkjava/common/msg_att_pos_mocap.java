/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE ATT_POS_MOCAP PACKING
package cn.hdu.usv.mavlink.mavlinkjava.common;
import cn.hdu.usv.mavlink.MAVLinkPacket;
import cn.hdu.usv.mavlink.messages.MAVLinkMessage;
import cn.hdu.usv.mavlink.messages.MAVLinkPayload;

/**
* Motion capture attitude and position
*/
public class msg_att_pos_mocap extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_ATT_POS_MOCAP = 138;
    public static final int MAVLINK_MSG_LENGTH = 36;
    private static final long serialVersionUID = MAVLINK_MSG_ID_ATT_POS_MOCAP;


      
    /**
    * Timestamp (micros since boot or Unix epoch)
    */
    public long time_usec;
      
    /**
    * Attitude quaternion (w, x, y, z order, zero-rotation is 1, 0, 0, 0)
    */
    public float q[] = new float[4];
      
    /**
    * X position in meters (NED)
    */
    public float x;
      
    /**
    * Y position in meters (NED)
    */
    public float y;
      
    /**
    * Z position in meters (NED)
    */
    public float z;
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH);
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_ATT_POS_MOCAP;
              
        packet.payload.putUnsignedLong(time_usec);
              
        
        for (int i = 0; i < q.length; i++) {
            packet.payload.putFloat(q[i]);
        }
                    
              
        packet.payload.putFloat(x);
              
        packet.payload.putFloat(y);
              
        packet.payload.putFloat(z);
        
        return packet;
    }

    /**
    * Decode a att_pos_mocap message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
              
        this.time_usec = payload.getUnsignedLong();
              
         
        for (int i = 0; i < this.q.length; i++) {
            this.q[i] = payload.getFloat();
        }
                
              
        this.x = payload.getFloat();
              
        this.y = payload.getFloat();
              
        this.z = payload.getFloat();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_att_pos_mocap(){
        msgid = MAVLINK_MSG_ID_ATT_POS_MOCAP;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_att_pos_mocap(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_ATT_POS_MOCAP;
        unpack(mavLinkPacket.payload);        
    }

              
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_ATT_POS_MOCAP - sysid:"+sysid+" compid:"+compid+" time_usec:"+time_usec+" q:"+q+" x:"+x+" y:"+y+" z:"+z+"";
    }
}
        