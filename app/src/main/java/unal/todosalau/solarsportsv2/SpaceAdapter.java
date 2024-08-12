package unal.todosalau.solarsportsv2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpaceAdapter extends RecyclerView.Adapter<SpaceAdapter.SpaceHolder>{
    private List<SportSpace> sportSpaceList;
    //private Context context;

    public SpaceAdapter(List<SportSpace> sportSpaceList){
        this.sportSpaceList = sportSpaceList;
        //loadSpacesFromPreferences();

    }


    @NonNull
    @Override
    public SpaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sportspace, parent, false);
        return new SpaceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceHolder holder, int position) {
        SportSpace space = sportSpaceList.get(position);
        holder.txvName.setText(space.getName());
        holder.txvAddress.setText("Dirección : " + space.getAddress());
        holder.txvCity.setText("Ciudad : " + space.getCity());
        holder.txvPhone.setText("Teléfono : " + space.getPhone());
        holder.txvPower.setText("Potencia Instalada : " + String.valueOf(space.getPower() + " Watts"));
        holder.txvEnergyGenerat.setText("Energia Generada : " + String.valueOf(space.getGenerated() + " Watts"));
        holder.txvEnergyConsum.setText("Energìa Consumida : " + String.valueOf(space.getConsumed() + " Watts"));
        holder.txvMonth.setText("Mes : " + space.getMonth());


    }

    @Override
    public int getItemCount() {
        return sportSpaceList.size();
    }


    public static class SpaceHolder extends RecyclerView.ViewHolder{

        TextView txvName, txvAddress, txvCity, txvPhone, txvPower, txvEnergyGenerat, txvEnergyConsum, txvMonth;


        public SpaceHolder (@NonNull View itemView) {
            super(itemView);

            txvName = itemView.findViewById(R.id.txvName);
            txvAddress = itemView.findViewById(R.id.txvAddress);
            txvCity = itemView.findViewById(R.id.txvCity);
            txvPhone = itemView.findViewById(R.id.txvPhone);
            txvPower = itemView.findViewById(R.id.txvPower);
            txvEnergyGenerat = itemView.findViewById(R.id.txvEnergyGenerat);
            txvEnergyConsum = itemView.findViewById(R.id.txvEnergyConsum);
            txvMonth = itemView.findViewById(R.id.txvMonth);

        }

    }

}
