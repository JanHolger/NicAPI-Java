package eu.bebendorf.nicapi.apis;

import com.google.gson.JsonObject;
import eu.bebendorf.nicapi.HttpClient;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.accounting.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eu.bebendorf.nicapi.HttpClient.map;

public class AccountingAPI {

    private final NicAPI nicAPI;

    public AccountingAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public Map<String, DomainPricing> getDomainPricing() throws NicAPIException {
        return HttpClient.toMap(nicAPI.get("accounting/pricing/domains").dataOrError(DomainPricingResponse.class).prices, DomainPricing.class);
    }

    public Map<String, DomainDiscount> getDomainDiscounts() throws NicAPIException {
        return HttpClient.toMap(nicAPI.get("accounting/pricing/domains/discounts").dataOrError(DomainDiscountResponse.class).discounts, DomainDiscount.class);
    }

    public TrafficPricing getTrafficPricing() throws NicAPIException {
        return nicAPI.get("accounting/pricing/traffic").dataOrError(TrafficPricing.class);
    }

    public VirtualServerPricing getVirtualServerPricing() throws NicAPIException {
        return nicAPI.get("accounting/pricing/vservers").dataOrError(VirtualServerPricing.class);
    }

    public TeamspeakPricing getTeamspeakPricing() throws NicAPIException {
        return nicAPI.get("accounting/pricing/teamspeak").dataOrError(TeamspeakPricing.class);
    }

    public SSLPricing[] getSSLPricing() throws NicAPIException {
        List<SSLPricing> pricings = new ArrayList<>();
        JsonObject prices = nicAPI.get("accounting/pricing/ssl").dataOrError(JsonObject.class).getAsJsonObject("prices");
        for(String type : prices.keySet()){
            for(String interval : prices.getAsJsonObject(type).keySet()){
                pricings.add(HttpClient.GSON.fromJson(prices.getAsJsonObject(type).getAsJsonObject(interval), SSLPricing.class));
            }
        }
        return pricings.toArray(new SSLPricing[0]);
    }

    public MulticraftPricing[] getMulticraftPricing() throws NicAPIException {
        return nicAPI.get("accounting/pricing/multicraft").dataOrError(MulticraftPricingResponse.class).units;
    }

    public DebitMandate getDebitMandate() throws NicAPIException {
        return nicAPI.get("accounting/debit").dataOrError(DebitMandate.class);
    }

    public void revokeDebitMandate() throws NicAPIException {
        nicAPI.post("accounting/debit/withdraw", null).orError();
    }

    public void createDebitMandate(String owner, String iban, String bic) throws NicAPIException {
        nicAPI.post("accounting/debit/create", map("owner", owner, "iban", iban, "bic", bic)).orError();
    }

    public AccountBalance getBalance() throws NicAPIException {
        return nicAPI.get("accounting/statistics/balance").dataOrError(AccountBalance.class);
    }

    public Billing[] getBillings() throws NicAPIException {
        return NicModel.set(nicAPI.get("accounting/billings").dataOrError(BillingListResponse.class).billings, nicAPI);
    }

    public BillingPosition[] getBillingPositions() throws NicAPIException {
        return nicAPI.get("accounting/billings/positions").dataOrError(BillingPositionListResponse.class).positions;
    }

    public BillingPosition getBillingPosition(int id) throws NicAPIException {
        return nicAPI.get("accounting/billings/positions/"+id).dataOrError(BillingPositionSingleResponse.class).position;
    }

    public Billing getBilling(String number) throws NicAPIException {
        return NicModel.set(nicAPI.get("accounting/billings/"+number).dataOrError(BillingSingleResponse.class).billing, nicAPI);
    }

    public byte[] downloadBilling(int id){
        return nicAPI.httpRequest("GET", "accounting/billings/"+id+"/download", new HashMap<>(), new HashMap<>(), null);
    }

    public Contract[] getContracts() throws NicAPIException {
        return NicModel.set(nicAPI.get("accounting/contracts").dataOrError(ContractListResponse.class).contracts, nicAPI);
    }

    public Contract getContract(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("accounting/contracts/"+id).dataOrError(ContractSingleResponse.class).contract, nicAPI);
    }

    public ContractPosition[] getContractPositions() throws NicAPIException {
        return NicModel.set(nicAPI.get("accounting/contracts/positions").dataOrError(ContractPositionListResponse.class).positions, nicAPI);
    }

    public ContractPosition getContractPosition(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("accounting/contracts/positions/"+id).dataOrError(ContractPositionSingleResponse.class).position, nicAPI);
    }

    public ContractPosition[] getContractPositions(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("accounting/contracts/"+id+"/positions").dataOrError(ContractPositionListResponse.class).positions, nicAPI);
    }

    public BillingPosition[] getContractBillingPositions(int id) throws NicAPIException {
        return nicAPI.get("accounting/contracts/"+id+"/last-billing-positions").dataOrError(BillingPositionListResponse.class).positions;
    }

    public Transaction[] getTransactions() throws NicAPIException {
        return nicAPI.get("accounting/transactions").dataOrError(TransactionListResponse.class).transactions;
    }

    public Transaction getTransaction(int id) throws NicAPIException {
        return nicAPI.get("accounting/transactions/"+id).dataOrError(TransactionSingleResponse.class).transaction;
    }

    private static class TransactionListResponse {
        public Transaction[] transactions;
    }

    private static class TransactionSingleResponse {
        public Transaction transaction;
    }

    private static class ContractListResponse {
        public Contract[] contracts;
    }

    private static class ContractSingleResponse {
        public Contract contract;
    }

    private static class ContractPositionListResponse {
        public ContractPosition[] positions;
    }

    private static class ContractPositionSingleResponse {
        public ContractPosition position;
    }

    private static class BillingListResponse {
        public Billing[] billings;
    }

    private static class BillingSingleResponse {
        public Billing billing;
    }

    private static class BillingPositionListResponse {
        public BillingPosition[] positions;
    }

    private static class BillingPositionSingleResponse {
        public BillingPosition position;
    }

    private static class MulticraftPricingResponse {
        public MulticraftPricing[] units;
    }

    private static class DomainPricingResponse {
        public JsonObject prices;
    }

    private static class DomainDiscountResponse {
        public JsonObject discounts;
    }

}
